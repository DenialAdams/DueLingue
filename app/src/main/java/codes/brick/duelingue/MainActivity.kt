package codes.brick.duelingue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import codes.brick.duelingue.endpoint.EndpointDataManager
import codes.brick.duelingue.endpoint.EndpointLangData
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private var verbs: List<Verb>? = null

    private val endpointDataManager: EndpointDataManager = EndpointDataManager()

    private var endpointData: Map<String, LangData>? = null

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMessage: Message) {
            endpointData = inputMessage.obj as Map<String, LangData>
            val quizBtn = findViewById<Button>(R.id.button_quiz)
            quizBtn?.isEnabled = true
            val showBtn = findViewById<Button>(R.id.button_show)
            showBtn?.isEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        endpointDataManager.initialFetch(gson, this)

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
