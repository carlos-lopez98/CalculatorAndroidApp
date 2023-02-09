package com.cjl.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //This is going to be where my result is displayed
    private var result: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
    }


    //The view is the button, that called the onDigit function
    fun onDigit(view: View){
        //Remember if that if our var is nullable we need to put the question mark in front
        result?.append((view as Button).text)
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()

        //Flags so our decimal point button works correctly
        lastNumeric = true
        lastDot =false
    }

    //Clears the screen
    fun onClear(view: View){
        result?.text = ""
    }

    fun onDecimal(view: View){
        if (lastNumeric && !lastDot){
            result?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator (view: View){
        //If the result textView is not empty, the let method will give us what it contains
        //Inside of the **it** variable
        result?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                result?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        }else {
                    value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}