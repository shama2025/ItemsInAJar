package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI that will take user input and solve for number of items using volume technique
 */

class VolObjActivity: AppCompatActivity() {
    // UI Variables
    private val calcBtn: Button by lazy {findViewById(R.id.calcBtn)}
    private val solutionTextView: TextView by lazy {findViewById(R.id.solutionTextView)}

    // Item in a jar Object
    private val items = mutableMapOf("width" to 0, "length" to 0, "height" to 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.vol_obj_activity)
        initVolObjActivity()
    }

    /**
     * Initializes the VolObjActivity
     */
    private fun initVolObjActivity() {
        calcBtnClickListener()
    }

    /**
     * This functions handles click event listener for calcBtn
     */
    private fun calcBtnClickListener(){
        calcBtn.setOnClickListener({
        // Get the EditText fields
        val objWidth = findViewById<EditText>(R.id.objWidthInput)
        val objHeight = findViewById<EditText>(R.id.objHeightInput)
        val objLength = findViewById<EditText>(R.id.objLengthInput)

        // Get the values from the EditText fields, handle empty or invalid inputs
        val width = objWidth.text.toString().toIntOrNull() ?: 0 // Default to 0 if invalid input
        val height = objHeight.text.toString().toIntOrNull() ?: 0
        val length = objLength.text.toString().toIntOrNull() ?: 0

        // Update the map with the parsed values
        items["width"] = width
        items["height"] = height
        items["length"] = length

        val volume = items["length"]?.let { items["height"]?.let { it1 ->
            items["width"]?.times(it)!!?.times(
                it1
            )
        } }

            solutionTextView.text = "Number of items in Jar: $volume"
        })
    }

}