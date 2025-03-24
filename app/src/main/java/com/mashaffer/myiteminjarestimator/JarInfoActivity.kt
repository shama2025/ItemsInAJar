package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for selecting the jar shape before proceeding to item shape selection.
 * Allows users to choose between rectangular prism and cylindrical jar shapes.
 */
class JarInfoActivity : AppCompatActivity() {

    // UI components for jar shape selection
    private val rectPrismJarBtn: ImageView by lazy { findViewById(R.id.rectPrisimJarBtn) }
    private val cylinderJarBtn: ImageView by lazy { findViewById(R.id.cylinderJarBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.jar_info_activity)

        setupJarShapeButtons()
    }

    /**
     * Sets up click listeners for jar shape selection buttons.
     * Configures navigation to the ItemShapeActivity with appropriate jar shape flags.
     */
    private fun setupJarShapeButtons() {
        setupCylinderJarButton()
        setupRectPrismJarButton()
    }

    /**
     * Configures the click listener for the cylindrical jar button.
     * Navigates to ItemShapeActivity with cylindrical jar configuration.
     */
    private fun setupCylinderJarButton() {
        cylinderJarBtn.setOnClickListener {
            navigateToItemShapeActivity(isCylinderJar = true)
        }
    }

    /**
     * Configures the click listener for the rectangular prism jar button.
     * Navigates to ItemShapeActivity with rectangular prism jar configuration.
     */
    private fun setupRectPrismJarButton() {
        rectPrismJarBtn.setOnClickListener {
            navigateToItemShapeActivity(isCylinderJar = false)
        }
    }

    /**
     * Navigates to ItemShapeActivity with the specified jar shape configuration.
     *
     * @param isCylinderJar Boolean flag indicating the jar shape (true for cylinder, false for rectangular prism)
     */
    private fun navigateToItemShapeActivity(isCylinderJar: Boolean) {
        // Create a bundle with jar shape configuration
        val jarShapeBundle = Bundle().apply {
            putBoolean("cylinderJar", isCylinderJar)
            putBoolean("rectPrismJar", !isCylinderJar)
        }

        // Create and start the intent to ItemShapeActivity
        val intent = Intent(this, ItemShapeActivity::class.java).apply {
            putExtras(jarShapeBundle)
            action = Intent.ACTION_VIEW
        }

        startActivity(intent)
    }
}