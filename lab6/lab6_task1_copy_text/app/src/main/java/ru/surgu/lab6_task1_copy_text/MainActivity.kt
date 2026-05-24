package ru.surgu.lab6_task1_copy_text

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val outputEditText = findViewById<EditText>(R.id.outputEditText)
        val copyButton = findViewById<Button>(R.id.copyButton)
        val closeButton = findViewById<Button>(R.id.closeButton)

        copyButton.setOnClickListener {
            outputEditText.setText(inputEditText.text.toString())
        }

        closeButton.setOnClickListener {
            finish()
        }
    }
}