package com.mashaffer.myiteminjarestimator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.time.times


/**
 * UI used to help user estimate number of items in a jar
 */
class SpherePackActivity: AppCompatActivity() {

    //UI Variables
    private val calcBtn: Button by lazy {findViewById(R.id.calcBtn)}
    private val solutionTextView: TextView by lazy {findViewById(R.id.solutionTextView)}
    private val itemWidthInput: TextView by lazy {findViewById(R.id.objWidthInput)}
    private val itemLengthInput: TextView by lazy{findViewById(R.id.objLengthInput)}
    private val itemHeightInput: TextView by lazy {findViewById(R.id.objHeightInput)}
    private val itemDiameterInput: TextView by lazy {findViewById(R.id.objDiameterInput)}
    private val jarHeightInput: TextView by lazy {findViewById(R.id.jarHeightInput)}
    private val jarLengthInput: TextView by lazy {findViewById(R.id.jarLengthInput)}
    private val jarWidthInput: TextView by lazy {findViewById(R.id.jarWidthInput)}
    private val jarDiameterInput: TextView by lazy {findViewById(R.id.jarDiameterInput)}

    companion object{
        private const val TAG = "SpherePackActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sphere_packing_activity)
        initSpherePackActivity()
    }

    /**
     * This function initializes the Sphere Pack Activity
     */
    private fun initSpherePackActivity() {

        val spherePackBundle = getBundle()

        initSpherePackForm(spherePackBundle)

        calcBtnClickListener(spherePackBundle)
    }

    /**
     * This function handles the enabling and disabling of various number inputs
     * based on the boolean values in the array
     */
    private fun initSpherePackForm(spherePackBundle: MutableMap<String,Boolean>) {

        if(spherePackBundle["eggShape"] == true){
            itemWidthInput.isEnabled = false
            itemLengthInput.isEnabled = false
            itemHeightInput.isEnabled = false
            itemDiameterInput.isEnabled = false
        }
        else if(spherePackBundle["sphereShape"] == true){
            itemWidthInput.isEnabled = false
            itemLengthInput.isEnabled = false
            itemHeightInput.isEnabled = false
        }
        else if(spherePackBundle["rectPrismShape"] == true){
            itemDiameterInput.isEnabled = false
        }
        else if(spherePackBundle["cylinderShape"] == true){
            itemWidthInput.isEnabled = false
            itemLengthInput.isEnabled = false
        }
        else if(spherePackBundle["cylinderJar"] == true){
            jarWidthInput.isEnabled = false
            jarLengthInput.isEnabled = false
        }
        else if(spherePackBundle["rectPrismJar"] == true){
            jarDiameterInput.isEnabled = false
        }
    }

    /**
     * This function returns the bundle that is passed via intent
     */
    private fun getBundle(): MutableMap<String,Boolean> {
        val bundle = intent.extras
        val spherePackForm = mutableMapOf(
            "cylinderJar" to (bundle?.getBoolean("cylinderJar", false) ?: false),
            "rectPrismJar" to (bundle?.getBoolean("rectPrismJar", false) ?: false),
            "eggShape" to (bundle?.getBoolean("eggShape", false) ?: false),
            "sphereShape" to (bundle?.getBoolean("sphereShape", false) ?: false),
            "rectPrismShape" to (bundle?.getBoolean("rectPrismShape", false) ?: false),
            "cylinderShape" to (bundle?.getBoolean("cylinderShape", false) ?: false)
        )
        Log.i(TAG, "Here is the bundle $spherePackForm")
        return spherePackForm
    }

    /**
     * This function handles the click listener for the calc button
     */
    @SuppressLint("SetTextI18n")
    private fun calcBtnClickListener(spherePackBundle: MutableMap<String,Boolean>) {
        calcBtn.setOnClickListener({
            Log.i(TAG, "User clicked the calculate button!")
            // Accessing the input values from the TextViews
            val itemWidth = itemWidthInput.text.toString() // String
            val itemLength = itemLengthInput.text.toString() // String
            val itemHeight = itemHeightInput.text.toString() // String
            val itemDiameter = itemDiameterInput.text.toString() // String
            val jarHeight = jarHeightInput.text.toString() // Uncomment if needed
            val jarLength = jarLengthInput.text.toString() // String
            val jarWidth = jarWidthInput.text.toString() // String
            val jarDiameter = jarDiameterInput.text.toString() // String

            val itemWidthFloat = itemWidth.toFloatOrNull() // Convert to Float
            val itemLengthFloat = itemLength.toFloatOrNull() // Convert to Float
            val itemHeightFloat = itemHeight.toFloatOrNull() // Convert to Float
            val itemDiameterFloat = itemDiameter.toFloatOrNull() // Convert to Float
            val jarLengthFloat = jarLength.toFloatOrNull() // Convert to Float
            val jarWidthFloat = jarWidth.toFloatOrNull() // Convert to Float
            val jarDiameterFloat = jarDiameter.toFloatOrNull() // Convert to Float
            val jarHeightFloat = jarHeight.toFloatOrNull() // Convert to Float

            // Consider using a 62% packing density
            // (Volume of jar / volume of a single item) * 0.62
            if(spherePackBundle["cylinderJar"] == true){
                // Safely calculate radius (diameter / 2)
                val radius = jarDiameterFloat?.div(2) ?: 0f

                // Safely handle the height (use default 0f if null)
                val height = jarHeightFloat ?: 0f

                // Calculate volume of the jar
                val volJar = Math.PI * radius * radius * height

                // get Volume of individual item
                val itemVol = getItemVolume(spherePackBundle,itemWidthFloat,itemHeightFloat,itemLengthFloat,itemDiameterFloat)
                solutionTextView.text = "Number of Items in Jar: ${((volJar.div(itemVol)).times(0.62))}"
            }
            else if(spherePackBundle["rectPrismJar"] == true) {
                // Solve using the rectangular prism equation
                val volJar = jarWidthFloat?.times(jarHeightFloat!!)?.times(jarLengthFloat!!)

                // Get volume of individual item
                val itemVol = getItemVolume(
                    spherePackBundle,
                    itemWidthFloat,
                    itemHeightFloat,
                    itemLengthFloat,
                    itemDiameterFloat
                )

                solutionTextView.text = "Number of Items in Jar: ${((volJar?.div(itemVol))?.times(0.62))}"
            }

        })
    }

    private fun getItemVolume(spherePackBundle: MutableMap<String, Boolean>,itemWidthFloat:Float?,itemHeightFloat: Float?, itemLengthFloat: Float?,itemDiameterFloat: Float? ): Double {
        if(spherePackBundle["eggShape"] == true){
            return 3.5325
        }else if(spherePackBundle["sphereShape"] == true){
            return 4/3 * Math.PI * ((itemDiameterFloat?.div(2)!!) * (itemDiameterFloat.div(2)))
        }else if(spherePackBundle["cylinderShape"] == true){
            return Math.PI * itemHeightFloat!! * (itemDiameterFloat?.div(2)!!)
        }
        else if(spherePackBundle["rectPrismShape"] == true){
            return (itemHeightFloat!! * itemLengthFloat!! * itemWidthFloat!!).toDouble()
        }
        return 0.0
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