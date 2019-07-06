package codes.brick.duelingue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private var verbs: List<Verb>? = null

    private fun loadVerb(verb: String, gson: Gson): Verb {
        val firstLetter = verb[0]
        val path = "verbs/italian/content/$firstLetter/$verb.json"
        val rawResource = assets.open(path)
        val reader = BufferedReader(InputStreamReader(rawResource))
        return gson.fromJson(reader, Verb::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val verbsToLoad = listOf("stare", "avere")
        val gson = Gson()
        verbs = verbsToLoad.mapNotNull {
            try {
                val verb = loadVerb(it, gson)
                Log.i(tag, "Successfully loaded verb: $it")
                verb

            } catch(e: Exception) {
                when(e) {
                    is IOException, is JsonParseException -> {
                        Log.e(tag, "Failed to load verb: $it", e)
                        null
                    }
                    else -> throw e
                }
            }
        }

        val quizBtn = findViewById<Button>(R.id.button_quiz)
        quizBtn?.setOnClickListener {
            val intent = Intent(this, QuizOptionsActivity::class.java)
            startActivity(intent)
        }
        val showBtn = findViewById<Button>(R.id.button_show)
        showBtn?.setOnClickListener {
            val intent = Intent(this, ShowOptionsActivity::class.java)
            startActivity(intent)
        }
    }
}
