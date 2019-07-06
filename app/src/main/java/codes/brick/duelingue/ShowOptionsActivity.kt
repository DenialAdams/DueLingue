package codes.brick.duelingue

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ShowOptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_options)

        val dropdownLang: Spinner = findViewById(R.id.show_options_lang)
        val langs: Array<String> = arrayOf("italian")
        val adapterLang: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, langs)
        dropdownLang.setAdapter(adapterLang)

        val dropdownGroups: Spinner = findViewById(R.id.show_options_groups)
        val groups: Array<String> = arrayOf("all", "basics", "are/ere/ire")
        val adapterGroups: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, groups)
        dropdownGroups.setAdapter(adapterGroups)

        val showBtn = findViewById<Button>(R.id.button_options_show)
        showBtn?.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }
}