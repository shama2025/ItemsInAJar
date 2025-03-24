package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for selecting the shape of an item to estimate its volume.
 *
 * This activity presents users with multiple shape options (egg, sphere, cylinder, rectangular prism)
 * and allows them to choose the shape that most closely resembles their item.
 *
 * @property eggShapeBtn ImageView for selecting egg-shaped item
 * @property sphereShapeBtn ImageView for selecting sphere-shaped item
 * @property cylinderShapeBtn ImageView for selecting cylinder-shaped item
 * @property rectPrismShapeBtn ImageView for selecting rectangular prism-shaped item
 */
class ItemShapeActivity : AppCompatActivity() {
    // UI element references initialized lazily to avoid null checks
    private val eggShapeBtn: ImageView by lazy { findViewById(R.id.itemShapeEgg) }
    private val sphereShapeBtn: ImageView by lazy { findViewById(R.id.itemShapeSphere) }
    private val cylinderShapeBtn: ImageView by lazy { findViewById(R.id.itemShapeCylinder) }
    private val rectPrismShapeBtn: ImageView by lazy { findViewById(R.id.itemShapeRectPrism) }

    companion object {
        // Logging tag for debugging purposes
        private const val TAG = "ItemShapeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display for modern Android design
        enableEdgeToEdge()

        // Set the layout for the shape selection screen
        setContentView(R.layout.item_shape_activity)

        // Initialize the activity's UI and click listeners
        initItemShapeActivity()
    }

    /**
     * Initializes the activity by preparing the bundle and setting up click listeners for shape buttons.
     *
     * This method does the following:
     * 1. Retrieves the initial bundle with shape selection preferences
     * 2. Sets up click listeners for each shape button
     */
    private fun initItemShapeActivity() {
        // Prepare the bundle with initial shape selection states
        val shapeSelectionBundle = prepareShapeBundle()

        // Set up click listeners for each shape button
        setupShapeButtonListeners(shapeSelectionBundle)
    }

    /**
     * Prepares a bundle with initial shape selection states based on intent extras.
     *
     * @return Bundle containing boolean flags for different shape options
     */
    private fun prepareShapeBundle(): Bundle {
        // Retrieve optional shape selection preferences from intent
        val needCylinder = intent.getBooleanExtra("cylinder", true)
        val needPrism = intent.getBooleanExtra("rectPrism", true)

        // Create a bundle with default shape selection states
        return Bundle().apply {
            putBoolean("cylinderJar", needCylinder)
            putBoolean("rectPrismJar", needPrism)
            // Initially, all shapes are considered valid
            putBoolean("eggShape", true)
            putBoolean("sphereShape", true)
            putBoolean("rectPrismShape", true)
            putBoolean("cylinderShape", true)
        }
    }

    /**
     * Sets up click listeners for all shape buttons.
     *
     * @param shapeBundle The bundle containing shape selection states
     */
    private fun setupShapeButtonListeners(shapeBundle: Bundle) {
        eggShapeBtn.setOnClickListener { handleShapeSelection(shapeBundle, ShapeType.EGG) }
        sphereShapeBtn.setOnClickListener { handleShapeSelection(shapeBundle, ShapeType.SPHERE) }
        cylinderShapeBtn.setOnClickListener { handleShapeSelection(shapeBundle, ShapeType.CYLINDER) }
        rectPrismShapeBtn.setOnClickListener { handleShapeSelection(shapeBundle, ShapeType.RECT_PRISM) }
    }

    /**
     * Handles the shape selection process when a shape button is clicked.
     *
     * @param shapeBundle The current bundle of shape selection states
     * @param selectedShape The shape type selected by the user
     */
    private fun handleShapeSelection(shapeBundle: Bundle, selectedShape: ShapeType) {
        // Log the selected shape for debugging
        Log.i(TAG, "User clicked the ${selectedShape.name.lowercase()} shape button!")

        // Update the bundle to disable other shapes
        val updatedBundle = shapeBundle.apply {
            when (selectedShape) {
                ShapeType.EGG -> {
                    putBoolean("rectPrismShape", false)
                    putBoolean("sphereShape", false)
                    putBoolean("cylinderShape", false)
                }
                ShapeType.SPHERE -> {
                    putBoolean("eggShape", false)
                    putBoolean("rectPrismShape", false)
                    putBoolean("cylinderShape", false)
                }
                ShapeType.CYLINDER -> {
                    putBoolean("eggShape", false)
                    putBoolean("sphereShape", false)
                    putBoolean("rectPrismShape", false)
                }
                ShapeType.RECT_PRISM -> {
                    putBoolean("eggShape", false)
                    putBoolean("sphereShape", false)
                    putBoolean("cylinderShape", false)
                }
            }
        }

        // Navigate to the next activity with the updated shape selection
        startActivity(
            Intent(this, SpherePackActivity::class.java)
                .putExtras(updatedBundle)
                .apply { action = Intent.ACTION_VIEW }
        )
    }

    /**
     * Enum representing different shape types for clearer code organization.
     */
    private enum class ShapeType {
        EGG, SPHERE, CYLINDER, RECT_PRISM
    }
}