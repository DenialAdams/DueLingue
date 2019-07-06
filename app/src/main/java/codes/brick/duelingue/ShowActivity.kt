package codes.brick.duelingue

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val titleView: TextView = findViewById(R.id.show_group_title)
        titleView.setText("basics")

        val table: TableLayout = findViewById(R.id.show_table)

        val words: Array<String> = arrayOf("essare", "avere", "stare")
        for (word in words) {
            val newRow = TableRow(this)
            val w = TextView(this)
            w.setText(word)
            val d = TextView(this)
            d.setText("def")
            newRow.addView(w)
            newRow.addView(d)
            table.addView(newRow)
        }
    }
}