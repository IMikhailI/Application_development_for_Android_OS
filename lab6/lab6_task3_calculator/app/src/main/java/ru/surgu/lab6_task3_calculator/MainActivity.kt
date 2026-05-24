package ru.surgu.lab6_task3_calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var operationTextView: TextView
    private lateinit var equalTextView: TextView
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firstNumberEditText = findViewById(R.id.firstNumberEditText)
        secondNumberEditText = findViewById(R.id.secondNumberEditText)
        operationTextView = findViewById(R.id.operationTextView)
        equalTextView = findViewById(R.id.equalTextView)
        resultTextView = findViewById(R.id.resultTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val closeButton = findViewById<Button>(R.id.closeButton)

        addButton.setOnClickListener { calculate("+") }
        subtractButton.setOnClickListener { calculate("-") }
        multiplyButton.setOnClickListener { calculate("*") }
        divideButton.setOnClickListener { calculate("/") }

        closeButton.setOnClickListener {
            finish()
        }

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearResult()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        firstNumberEditText.addTextChangedListener(watcher)
        secondNumberEditText.addTextChangedListener(watcher)
    }

    private fun calculate(operation: String) {
        val first = firstNumberEditText.text.toString().toDoubleOrNull()
        val second = secondNumberEditText.text.toString().toDoubleOrNull()

        if (first == null || second == null) {
            Toast.makeText(this, "Введите два числа", Toast.LENGTH_SHORT).show()
            return
        }

        if (operation == "/" && second == 0.0) {
            Toast.makeText(this, "Деление на ноль невозможно", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> 0.0
        }

        operationTextView.text = operation
        equalTextView.text = "="
        resultTextView.text = result.toString()
    }

    private fun clearResult() {
        operationTextView.text = ""
        equalTextView.text = ""
        resultTextView.text = ""
    }
}