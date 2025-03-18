package com.mashaffer.myiteminjarestimator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

/**
 * UI that allows user to pick shape item closely resembles
 */
class ItemShapeActivity: AppCompatActivity() {
    //UI Variables
    private val eggShapeBtn: ImageView by lazy {findViewById(R.id.itemShapeEgg)}
    private val sphereShapeBtn: ImageView by lazy {findViewById(R.id.itemShapeSphere)}
    private val cylinderShapeBtn: ImageView by lazy {findViewById(R.id.itemShapeCylinder)}
    private val rectPrismShapeBtn: ImageView by lazy {findViewById(R.id.itemShapeRectPrism)}

    companion object{
        private const val TAG = "ItemShapeActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_shape_activity)
        initItemShapeActivity()
    }

    /**
     * This function initializes the ItemShapeActivity
     */
    private fun initItemShapeActivity() {
        val spherePackForm: Bundle = getBundle()

        eggShapeBtnClickListener(spherePackForm)

        sphereShapeBtnClickListener(spherePackForm)

        cylinderShapeBtnClickListener(spherePackForm)

        rectPrismShapeBtnClickListener(spherePackForm)
    }

    /**
     * This function gets the value from the intent
     */
    private fun getBundle(): Bundle{
        val needCylinder = intent.getBooleanExtra("cylinder",true)
        val needPrism = intent.getBooleanExtra("rectPrism", true)

        val spherePackForm = Bundle().apply {
            putBoolean("cylinderJar", needCylinder)
            putBoolean("rectPrismJar", needPrism)
            putBoolean("eggShape", true)
            putBoolean("sphereShape", true)
            putBoolean("rectPrismShape", true)
            putBoolean("cylinderShape", true)
        }
        return spherePackForm
    }

    /**
     * This function handles the click listener for the rect-prism image button
     */
    private fun rectPrismShapeBtnClickListener(spherePackForm: Bundle) {
        rectPrismShapeBtn.setOnClickListener({
            // Update the form bundle and then pass it to activity
            Log.i(TAG, "User clicked the rect-prism shape button!")
            spherePackForm.apply {
                putBoolean("eggShape", false)
                putBoolean("sphereShape", false)
                putBoolean("cylinderShape", false)
            }
            startActivity(Intent(this,SpherePackActivity::class.java).putExtras(spherePackForm).apply { action=Intent.ACTION_VIEW })
        })
    }

    /**
     * This function handles the click listener for the cylinder image button
     */
    private fun cylinderShapeBtnClickListener(spherePackForm: Bundle) {
        cylinderShapeBtn.setOnClickListener({
            // Update the form bundle and then pass it to activity
            Log.i(TAG, "User clicked the cylinder shape button!")
            spherePackForm.apply {
                putBoolean("eggShape", false)
                putBoolean("sphereShape", false)
                putBoolean("rectPrismShape", false)
            }
            startActivity(Intent(this,SpherePackActivity::class.java).putExtras(spherePackForm).apply { action=Intent.ACTION_VIEW })
        })
    }

    /**
     * This function handles the click listener for the sphere image button
     */
    private fun sphereShapeBtnClickListener(spherePackForm: Bundle) {
        sphereShapeBtn.setOnClickListener({
            // Update the form bundle and then pass it to activity
            Log.i(TAG, "User clicked the sphere shape button!")
            spherePackForm.apply {
                putBoolean("eggShape", false)
                putBoolean("rectPrismShape", false)
                putBoolean("cylinderShape", false)
            }
            startActivity(Intent(this,SpherePackActivity::class.java).putExtras(spherePackForm).apply { action=Intent.ACTION_VIEW })

        })
    }

    /**
     * This function handles the click listener for the egg shape button
     */
    private fun eggShapeBtnClickListener(spherePackForm: Bundle) {
        eggShapeBtn.setOnClickListener({
            // Update the form bundle and then pass it to activity
            Log.i(TAG, "User clicked the egg shape button!")
            spherePackForm.apply {
                putBoolean("rectPrismShape", false)
                putBoolean("sphereShape", false)
                putBoolean("cylinderShape", false)
            }
            startActivity(Intent(this,SpherePackActivity::class.java).putExtras(spherePackForm).apply { action=Intent.ACTION_VIEW })
        })
    }

}