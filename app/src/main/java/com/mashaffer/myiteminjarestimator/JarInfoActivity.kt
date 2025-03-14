package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI to gain more info regarding to general shape of jar
 */
class JarInfoActivity: AppCompatActivity() {

    // Ui Variables
    private val rectPrismJarBtn:ImageView by lazy {findViewById(R.id.rectPrisimJarBtn)}
    private val cylinderJarBtn:ImageView by lazy {findViewById(R.id.cylinderJarBtn)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.jar_info_activity)

        initJarInfoActivity()
    }

    /**
     * This function initializes the components of the JarInfoActivity
     */
    private fun initJarInfoActivity() {
        rectPrismJarBtnClickListener()

        cylinderJarBtnClickListener()
    }

    /**
     * This function handles the cylinderJarBtn click listener
     */
    private fun cylinderJarBtnClickListener() {
        cylinderJarBtn.setOnClickListener({
            val jarBundle: Bundle = Bundle().apply{
                putBoolean("cylinder", true)
                putBoolean("rectPrism", false)
            }
            startActivity(Intent(this,ItemShapeActivity::class.java).putExtras(jarBundle).apply{action=Intent.ACTION_VIEW})

        })
    }

    /**
     * This functions handles the rectPrism click listener
     */
    private fun rectPrismJarBtnClickListener() {
        rectPrismJarBtn.setOnClickListener({
            val jarBundle: Bundle = Bundle().apply{
                putBoolean("cylinder", false)
                putBoolean("rectPrism", true)
            }
            startActivity(Intent(this,ItemShapeActivity::class.java).putExtras(jarBundle).apply{action=Intent.ACTION_VIEW})
        })
}

}