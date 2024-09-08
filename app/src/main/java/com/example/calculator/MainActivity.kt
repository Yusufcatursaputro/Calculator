package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayOperation: TextView
    private lateinit var displayResult: TextView
    private var currentInput = ""
    private var operation = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayOperation = findViewById(R.id.operasi)
        displayResult = findViewById(R.id.hasil)

        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.tambah_btn, R.id.kurang_btn,
            R.id.kali_btn, R.id.bagi_btn, R.id.persen_btn, R.id.titik_btn
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }

        findViewById<Button>(R.id.ac_btn).setOnClickListener { clear() }
        findViewById<Button>(R.id.hasil_btn).setOnClickListener { calculateResult() }
    }

    private fun onButtonClick(button: Button) {
        val value = button.text.toString()

        if (value in listOf("+", "-", "X", "/", "%")) {
            if (currentInput.isNotEmpty()) {
                result = currentInput.toDouble()
                currentInput = ""
                operation = value
                displayOperation.text = "$result $operation"
            }
        } else {
            currentInput += value
            displayOperation.text = "$result $operation $currentInput"
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operation.isNotEmpty()) {
            val currentValue = currentInput.toDouble()

            result = when (operation) {
                "+" -> result + currentValue
                "-" -> result - currentValue
                "X" -> result * currentValue
                "/" -> result / currentValue
                "%" -> result % currentValue
                else -> result
            }

            displayResult.text = result.toString()
            displayOperation.text = ""
            currentInput = ""
            operation = ""
        }
    }

    private fun clear() {
        currentInput = ""
        operation = ""
        result = 0.0
        displayOperation.text = ""
        displayResult.text = "0"
    }
}
