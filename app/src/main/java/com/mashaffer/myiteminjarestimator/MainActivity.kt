package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity serves as the home screen for the Jar Item Estimator app.
 * It provides two primary methods for estimating the number of items in a jar:
 * 1. Sphere Packing Method
 * 2. Volume of Objects Method
 */
class MainActivity : AppCompatActivity() {
    // UI buttons for different estimation methods
    private val spherePackingButton: Button by lazy { findViewById(R.id.spherePackBtn) }
    private val volumeObjectButton: Button by lazy { findViewById(R.id.objVolBtn) }

    companion object {
        // Logging tag for tracking events in this activity
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        // Initialize button click listeners
        setupButtonListeners()
    }

    /**
     * Sets up click listeners for both estimation method buttons.
     * Navigates to the corresponding activity when a button is clicked.
     */
    private fun setupButtonListeners() {
        // Set up listener for sphere packing method button
        spherePackingButton.setOnClickListener {
            navigateToJarInfoActivity()
        }

        // Set up listener for volume of objects method button
        volumeObjectButton.setOnClickListener {
            navigateToVolumeObjectActivity()
        }
    }

    /**
     * Navigates to the Jar Info Activity for sphere packing method.
     * Logs the user's navigation action.
     */
    private fun navigateToJarInfoActivity() {
        startActivity(Intent(this, JarInfoActivity::class.java))
        Log.i(TAG, "User selected sphere packing method")
    }

    /**
     * Navigates to the Volume of Objects Activity.
     * Logs the user's navigation action.
     */
    private fun navigateToVolumeObjectActivity() {
        startActivity(Intent(this, VolObjActivity::class.java))
        Log.i(TAG, "User selected volume of objects method")
    }
}