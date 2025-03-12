package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * Home UI for App where user picks method of estimating number of items in a jar
 */
class MainActivity : AppCompatActivity() {
    // UI Variables
    private val sphereBtn: Button by lazy { findViewById(R.id.spherePackBtn) }
    private val volObjBtn: Button by lazy { findViewById(R.id.objVolBtn)}

    companion object{
        private const val TAG = "Main Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        initMainActivty()
    }

    /**
     * This function will initialize the Main Activity
     */
    private fun initMainActivty(){

        // Function call for sphere button
        spherePackClickListener()

        // Function call for volume obj button
        volObjClickListener()
    }

    /**
     * This function handles the click event listener for the sphere packing
     * button
     */
    private fun spherePackClickListener(){
        sphereBtn.setOnClickListener({
            // If clicked navigate to the jar info activity
            startActivity(Intent(this,JarInfoActivity::class.java))
            Log.i(TAG, "User clicked the sphere button")
        })

    }

    /**
     * This function handles the click event listener for the volume of obj
     * button
     */
    private fun volObjClickListener(){
        volObjBtn.setOnClickListener({
            // If clicked navigate to the volume of items calculator
            startActivity(Intent(this,VolObjActivity::class.java))
            Log.i(TAG, "User clicked the vol obj button")
        })

    }
}

