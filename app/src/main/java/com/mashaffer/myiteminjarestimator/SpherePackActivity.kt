package com.mashaffer.myiteminjarestimator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for estimating the number of items that can fit in a jar using various shape calculations.
 * Supports different item and jar shapes including spheres, cylinders, rectangular prisms, and eggs.
 */
class SpherePackActivity : AppCompatActivity() {

    // UI component lazy initializations
    private val calcBtn: Button by lazy { findViewById(R.id.calcBtn) }

    // Item dimension inputs
    private val itemWidthInput: TextView by lazy { findViewById(R.id.objWidthInput) }
    private val itemLengthInput: TextView by lazy { findViewById(R.id.objLengthInput) }
    private val itemHeightInput: TextView by lazy { findViewById(R.id.objHeightInput) }
    private val itemDiameterInput: TextView by lazy { findViewById(R.id.objDiameterInput) }

    // Jar dimension inputs
    private val jarHeightInput: TextView by lazy { findViewById(R.id.jarHeightInput) }
    private val jarLengthInput: TextView by lazy { findViewById(R.id.jarLengthInput) }
    private val jarWidthInput: TextView by lazy { findViewById(R.id.jarWidthInput) }
    private val jarDiameterInput: TextView by lazy { findViewById(R.id.jarDiameterInput) }

    companion object {
        private const val TAG = "SpherePackActivity"

        // Packing density constants for different shapes
        private const val EGG_PACKING_DENSITY = 0.8
        private const val SPHERE_PACKING_DENSITY = 0.73
        private const val CYLINDER_PACKING_DENSITY = 0.72
        private const val RECT_PRISM_PACKING_DENSITY = 0.64
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sphere_packing_activity)
        initSpherePackActivity()
    }

    /**
     * Initializes the Sphere Packing Activity by:
     * 1. Retrieving shape configuration from the intent
     * 2. Setting up the form based on selected shapes
     * 3. Setting up the calculate button listener
     */
    private fun initSpherePackActivity() {
        val spherePackBundle = getShapeConfigurationBundle()
        initSpherePackForm(spherePackBundle)
        setupCalculateButtonListener(spherePackBundle)
    }

    /**
     * Configures form input fields based on the selected shapes,
     * disabling irrelevant input fields for the chosen configuration.
     */
    private fun initSpherePackForm(spherePackBundle: Map<String, Boolean>) {
        when {
            spherePackBundle["eggShape"] == true -> {
                itemWidthInput.isEnabled = false
                itemLengthInput.isEnabled = false
                itemHeightInput.isEnabled = false
                itemDiameterInput.isEnabled = false
            }
            spherePackBundle["sphereShape"] == true -> {
                itemWidthInput.isEnabled = false
                itemLengthInput.isEnabled = false
                itemHeightInput.isEnabled = false
            }
            spherePackBundle["rectPrismShape"] == true -> {
                itemDiameterInput.isEnabled = false
            }
            spherePackBundle["cylinderShape"] == true -> {
                itemWidthInput.isEnabled = false
                itemLengthInput.isEnabled = false
            }
            spherePackBundle["cylinderJar"] == true -> {
                jarWidthInput.isEnabled = false
                jarLengthInput.isEnabled = false
            }
            spherePackBundle["rectPrismJar"] == true -> {
                jarDiameterInput.isEnabled = false
            }
        }
    }

    /**
     * Retrieves shape configuration from the intent extras.
     * @return A mutable map of shape configuration boolean values
     */
    private fun getShapeConfigurationBundle(): MutableMap<String, Boolean> {
        val bundle = intent.extras
        return mutableMapOf(
            "cylinderJar" to (bundle?.getBoolean("cylinderJar", false) ?: false),
            "rectPrismJar" to (bundle?.getBoolean("rectPrismJar", false) ?: false),
            "eggShape" to (bundle?.getBoolean("eggShape", false) ?: false),
            "sphereShape" to (bundle?.getBoolean("sphereShape", false) ?: false),
            "rectPrismShape" to (bundle?.getBoolean("rectPrismShape", false) ?: false),
            "cylinderShape" to (bundle?.getBoolean("cylinderShape", false) ?: false)
        ).also {
            Log.i(TAG, "Shape configuration: $it")
        }
    }

    /**
     * Sets up the calculate button click listener to estimate the number of items in the jar.
     * Calculates based on jar and item volumes with a packing density factor.
     */
    @SuppressLint("SetTextI18n")
    private fun setupCalculateButtonListener(spherePackBundle: Map<String, Boolean>) {
        calcBtn.setOnClickListener {
            Log.i(TAG, "Calculate button clicked!")

            // Convert input values to floats, handling potential null cases
            val inputs = extractAndConvertInputs()

            when {
                spherePackBundle["cylinderJar"] == true -> calculateCylinderJarVolume(inputs)
                spherePackBundle["rectPrismJar"] == true -> calculateRectPrismJarVolume(inputs)
            }
        }
    }

    /**
     * Extracts and converts input values from TextViews to Float.
     * @return A map of converted input values
     */
    private fun extractAndConvertInputs(): Map<String, Float?> = mapOf(
        "itemWidth" to itemWidthInput.text.toString().toFloatOrNull(),
        "itemLength" to itemLengthInput.text.toString().toFloatOrNull(),
        "itemHeight" to itemHeightInput.text.toString().toFloatOrNull(),
        "itemDiameter" to itemDiameterInput.text.toString().toFloatOrNull(),
        "jarHeight" to jarHeightInput.text.toString().toFloatOrNull(),
        "jarLength" to jarLengthInput.text.toString().toFloatOrNull(),
        "jarWidth" to jarWidthInput.text.toString().toFloatOrNull(),
        "jarDiameter" to jarDiameterInput.text.toString().toFloatOrNull()
    )

    /**
     * Calculates the volume and number of items for a cylindrical jar.
     */
    private fun calculateCylinderJarVolume(inputs: Map<String, Float?>) {
        val radius = inputs["jarDiameter"]?.div(2) ?: 0f
        val height = inputs["jarHeight"] ?: 0f
        val jarVolume = Math.PI * (radius * radius) * height

        val (itemVolume, packingDensity) = getItemVolumeAndDensity(
            inputs["itemWidth"],
            inputs["itemHeight"],
            inputs["itemLength"],
            inputs["itemDiameter"]
        )

        val estimatedItemCount = ((jarVolume * packingDensity) / itemVolume).toInt()
        displayItemCount(estimatedItemCount)
    }

    /**
     * Calculates the volume and number of items for a rectangular prism jar.
     */
    private fun calculateRectPrismJarVolume(inputs: Map<String, Float?>) {
        val jarVolume = inputs["jarWidth"]
            ?.times(inputs["jarHeight"] ?: 0f)
            ?.times(inputs["jarLength"] ?: 0f)
            ?: 0f

        val (itemVolume, packingDensity) = getItemVolumeAndDensity(
            inputs["itemWidth"],
            inputs["itemHeight"],
            inputs["itemLength"],
            inputs["itemDiameter"]
        )

        val estimatedItemCount = ((jarVolume * packingDensity) / itemVolume).toInt()
        displayItemCount(estimatedItemCount)
    }

    /**
     * Calculates the volume and packing density for different item shapes.
     * @return A pair of item volume and packing density
     */
    private fun getItemVolumeAndDensity(
        itemWidth: Float?,
        itemHeight: Float?,
        itemLength: Float?,
        itemDiameter: Float?
    ): Pair<Double, Double> = when {
        intent.extras?.getBoolean("eggShape", false) == true ->
            Pair(0.72, EGG_PACKING_DENSITY)

        intent.extras?.getBoolean("sphereShape", false) == true -> {
            val radius = itemDiameter?.div(2) ?: 0f
            Pair(
                (4.0/3.0 * Math.PI * radius * radius).toDouble(),
                SPHERE_PACKING_DENSITY
            )
        }

        intent.extras?.getBoolean("cylinderShape", false) == true ->
            Pair(
                Math.PI * (itemHeight ?: 0f) * (itemDiameter?.div(2) ?: 0f).toDouble(),
                CYLINDER_PACKING_DENSITY
            )

        intent.extras?.getBoolean("rectPrismShape", false) == true ->
            Pair(
                ((itemHeight ?: 0f) * (itemLength ?: 0f) * (itemWidth ?: 0f)).toDouble(),
                RECT_PRISM_PACKING_DENSITY
            )

        else -> Pair(0.0, 0.0)
    }
    /**
     * Displays a dialog showing the estimated item count to the user.
     *
     * @param estimatedItemCount The number of items to display in the dialog
     */
    private fun displayItemCount(estimatedItemCount: Int) {
        // Create dialog builder with current context
        val alertDialog = AlertDialog.Builder(this)

        // Inflate custom layout for the dialog
        val dialogView = layoutInflater.inflate(R.layout.item_count, null)

        // Find and set the text for the item count TextView
        dialogView.findViewById<TextView>(R.id.itemCount).apply {
            text = "Number of items: $estimatedItemCount"
        }

        // Configure the dialog with the custom view and a close button
        alertDialog.apply {
            setView(dialogView)
            setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }
            // Create and show the dialog in one step
            show()
        }
    }
}