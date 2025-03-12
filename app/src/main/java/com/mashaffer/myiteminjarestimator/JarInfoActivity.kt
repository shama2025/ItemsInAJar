package com.mashaffer.myiteminjarestimator

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI to gain more info regarding to general shape of jar
 */
class JarInfoActivity: AppCompatActivity() {
    // Ui Variables
    private val rectPrismJarBtn:ImageButton by lazy {findViewById(R.id.rectPrisimJarBtn)}
    private val cylinderJarBtn:ImageButton by lazy {findViewById(R.id.cylinderJarBtn)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.jar_info_activity)

        initJarInfoActivity()
    }

    private fun initJarInfoActivity() {
        TODO("Not yet implemented")
    }

}