package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI that allows user to pick shape item closely resembles
 */
class ItemShapeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_shape_activity)

    }

    /**
     * List of shapes:
     * Egg
     * cylinder
     * sphere
     * rectangular prism
     */
}