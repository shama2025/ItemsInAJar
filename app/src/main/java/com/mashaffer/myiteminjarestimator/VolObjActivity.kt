package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * VolObjActivity allows users to estimate the number of items in a jar
 * by calculating the volume of individual objects and the jar.
 *
 * This activity provides input fields for object dimensions and calculates
 * an estimated item count based on volume calculations.
 */
class VolObjActivity : AppCompatActivity() {
    // UI components
    private lateinit var calcButton: Button
    private lateinit var solutionTextView: TextView

    // Input fields for object dimensions
    private lateinit var widthInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var lengthInput: EditText

    // Object dimensions storage
    private val objectDimensions = mutableMapOf(
        "width" to 0,
        "length" to 0,
        "height" to 0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.vol_obj_activity)

        // Initialize UI components and set up listeners
        initializeComponents()
    }

    /**
     * Initializes all UI components and sets up event listeners.
     */
    private fun initializeComponents() {
        // Find and initialize UI elements
        calcButton = findViewById(R.id.calcBtn)
        solutionTextView = findViewById(R.id.solutionTextView)
        widthInput = findViewById(R.id.objWidthInput)
        heightInput = findViewById(R.id.objHeightInput)
        lengthInput = findViewById(R.id.objLengthInput)

        // Set up calculate button click listener
        setupCalculateButtonListener()
    }

    /**
     * Configures the calculate button click listener.
     * Validates input, calculates object volume, and displays the result.
     */
    private fun setupCalculateButtonListener() {
        calcButton.setOnClickListener {
            // Parse and validate input dimensions
            val width = parseInputToInt(widthInput)
            val height = parseInputToInt(heightInput)
            val length = parseInputToInt(lengthInput)

            // Update dimensions map
            objectDimensions.apply {
                this["width"] = width
                this["height"] = height
                this["length"] = length
            }

            // Calculate volume and update solution text
            calculateAndDisplayVolume(width, height, length)
        }
    }

    /**
     * Parses input from an EditText to an integer,
     * returning 0 for empty or invalid inputs.
     *
     * @param input EditText containing the input to parse
     * @return Parsed integer value or 0 if invalid
     */
    private fun parseInputToInt(input: EditText): Int {
        return input.text.toString().toIntOrNull() ?: 0
    }

    /**
     * Calculates the volume based on object dimensions and updates the solution TextView.
     *
     * @param width Width of the object
     * @param height Height of the object
     * @param length Length of the object
     */
    private fun calculateAndDisplayVolume(width: Int, height: Int, length: Int) {
        val volume = width * height * length
        solutionTextView.text = "Number of items in Jar: $volume"
    }
}