package com.example.wordcounterapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Meaningful variable names (Requirement #1)
    private lateinit var txtInput: EditText
    private lateinit var spinnerCountType: Spinner
    private lateinit var btnCount: Button
    private lateinit var txtResult: TextView

    // Instance of our TextCounter class
    private val textCounter = TextCounter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        initializeViews()

        // Set up the spinner with options
        setupSpinner()

        // Set up button click listener
        setupButtonListener()
    }

    /**
     * Initialize all view references with meaningful names
     */
    private fun initializeViews() {
        txtInput = findViewById(R.id.txtInput)
        spinnerCountType = findViewById(R.id.spinnerCountType)
        btnCount = findViewById(R.id.btnCount)
        txtResult = findViewById(R.id.txtResult)
    }

    /**
     * Set up the spinner with counting options
     */
    private fun setupSpinner() {
        // Create array of options from string resources
        val countOptions = arrayOf(
            getString(R.string.count_sentences),
            getString(R.string.count_words),
            getString(R.string.count_chars),
            getString(R.string.count_numbers)
        )

        // Create and set adapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            countOptions
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountType.adapter = adapter
    }

    /**
     * Set up the button click listener for counting
     */
    private fun setupButtonListener() {
        btnCount.setOnClickListener {
            performCount()
        }
    }

    /**
     * Performs the counting operation based on selected option
     */
    private fun performCount() {
        val inputText = txtInput.text.toString()

        // Validation: Check if text is empty (Requirement #9)
        if (inputText.trim().isEmpty()) {
            showEmptyTextWarning()
            return
        }

        // Get selected option from spinner
        val selectedOption = spinnerCountType.selectedItem.toString()

        // Perform counting based on selection
        val result = when (selectedOption) {
            getString(R.string.count_sentences) -> {
                textCounter.countSentences(inputText)
            }
            getString(R.string.count_words) -> {
                textCounter.countWords(inputText)
            }
            getString(R.string.count_chars) -> {
                textCounter.countCharacters(inputText)
            }
            getString(R.string.count_numbers) -> {
                textCounter.countNumbers(inputText)
            }
            else -> 0
        }

        // Display result
        displayResult(selectedOption, result)
    }

    /**
     * Shows a Toast warning for empty text (Requirement #9)
     */
    private fun showEmptyTextWarning() {
        Toast.makeText(
            this,
            getString(R.string.empty_text_warning),
            Toast.LENGTH_SHORT
        ).show()

        // Clear result
        txtResult.text = getString(R.string.result_label)
    }

    /**
     * Displays the counting result in the TextView
     */
    private fun displayResult(countType: String, count: Int) {
        val resultText = getString(R.string.result_format, countType, count)
        txtResult.text = resultText
    }
}