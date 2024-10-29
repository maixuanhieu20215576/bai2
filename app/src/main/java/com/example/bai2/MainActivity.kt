package com.example.bai2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            processInput()
        }
    }

    private fun processInput() {
        val input = editTextNumber.text.toString();

        if (input.isEmpty()) {
            showError("Vui lòng nhập số!")
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            showError("Vui lòng nhập số nguyên dương!")
            return
        }

        when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> showEvenNumbers(n)
            R.id.radioOdd -> showOddNumbers(n)
            R.id.radioSquare -> showSquareNumbers(n)
            else -> showError("Vui lòng chọn loại số!")
        }
    }

    private fun showEvenNumbers(n: Int) {
        val numbers = (0..n).filter { it % 2 == 0 }
        displayNumbers(numbers)
    }

    private fun showOddNumbers(n: Int) {
        val numbers = (1..n).filter { it % 2 != 0 }
        displayNumbers(numbers)
    }

    private fun showSquareNumbers(n: Int) {
        val numbers = (0..n).filter { isPerfectSquare(it) }
        displayNumbers(numbers)
    }

    private fun isPerfectSquare(n: Int): Boolean {
        val sqrt = kotlin.math.sqrt(n.toDouble()).toInt()
        return sqrt * sqrt == n
    }

    private fun displayNumbers(numbers: List<Int>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter
        textViewError.text = ""
    }

    private fun showError(message: String) {
        textViewError.text = message
        listView.adapter = null
    }
}