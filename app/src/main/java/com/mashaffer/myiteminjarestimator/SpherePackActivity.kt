package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI used to help user estimate number of items in a jar
 */
class SpherePackActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sphere_packing_activity)

    }
    /**
     * For egg/jellybean shape use 3.5325
     * For sphere shape get the diameter
     * For cylinder shape get diameter and height
     * For rect prism shape get the l,w,h
     *
     * For the 2 jar shapes get the l,w,h (rect prism jar) or the diameter or height (cylinder jar)
     *
     */
}