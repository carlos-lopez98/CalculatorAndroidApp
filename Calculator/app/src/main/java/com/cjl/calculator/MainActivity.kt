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
    }

    //Clears the screen
    fun onClear(view: View){
        result?.text = ""
    }
}