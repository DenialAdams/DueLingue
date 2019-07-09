package codes.brick.duelingue.endpoint

import android.content.Context
import android.os.Handler
import codes.brick.duelingue.LangData
import codes.brick.duelingue.Verb
import codes.brick.duelingue.VerbGroup
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import android.util.Log
import com.google.gson.JsonParseException


class EndpointDataManager {
    private val client = OkHttpClient()

    private val endpoint = "https://www.brick.codes/DueLingue/data.json"

    private val tag = "EndpointDataManager"

    private fun loadVerb(verb: String, gson: Gson, context: Context): Verb {
        val firstLetter = verb[0]
        val path = "verbs/italian/content/$firstLetter/$verb.json"
        val rawResource = context.assets.open(path)
        val reader = BufferedReader(InputStreamReader(rawResource))
        return gson.fromJson(reader, Verb::class.java)
    }

    private fun tryLoadVerb(verb_s: String, gson: Gson, context: Context): Verb? {
        var verb: Verb? = null
        try {
            verb = loadVerb(verb_s, gson, context)
            Log.i(tag, "Successfully loaded verb: $verb_s")
        } catch(e: Exception) {
            when(e) {
                is IOException, is JsonParseException -> {
                    Log.e(tag, "Failed to load verb: $verb_s", e)
                }
                else -> throw e
            }
        }
        return verb
    }

    private fun parseAndPost(endpointData: List<EndpointLangData>, gson: Gson, context: Context) {
        val langData: Map<String, LangData> = endpointData.associate {
            it.language to LangData(
                it.tenses, it.verb_groups.map { vged ->
                    VerbGroup(vged.name, vged.verbs.mapNotNull { s -> tryLoadVerb(s, gson, context) })
                }
            )
        }
        val mainHandler = Handler(context.mainLooper)
        mainHandler.obtainMessage(0, langData).apply { sendToTarget() }
    }

    fun initialFetch(gson: Gson, context: Context) {
        // TODO: if we have a cached file load that immediately to unblock the user
        // otherwise, enqueue request
        val request = Request.Builder().url(endpoint).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                // TODO
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    // TODO
                    return
                }
                // TODO: cache response body
                val endpointData: List<EndpointLangData> = gson.fromJson(response.body!!.charStream(), object: TypeToken<List<EndpointLangData>>(){}.type)
                parseAndPost(endpointData, gson, context)
            }
        })
    }
}