package ru.surgu.lab6_task2_buttons

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val allowButton = findViewById<Button>(R.id.allowButton)
        val showButton = findViewById<Button>(R.id.showButton)
        val closeButton = findViewById<Button>(R.id.closeButton)

        allowButton.setOnClickListener {
            showButton.isEnabled = true
        }

        showButton.setOnClickListener {
            closeButton.visibility = View.VISIBLE
        }

        closeButton.setOnClickListener {
            finish()
        }
    }
}