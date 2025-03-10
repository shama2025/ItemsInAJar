package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI that will take user input and solve for number of items using volume technique
 */

class VolObjActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.vol_obj_activity)

    }
}