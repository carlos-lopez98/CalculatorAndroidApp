package com.cjl.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

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

        //Flags so our decimal point button works correctly
        lastNumeric = true
        lastDot =false
    }

    //Clears the screen
    fun onClear(view: View){
        result?.text = ""
    }

    //Adds Decimal Functionality
    fun onDecimal(view: View){
        if (lastNumeric && !lastDot){
            result?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    //Checks to see if expression has an operator
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

    //Runs the needed operation
    fun onEqual(view: View){
        if(lastNumeric){
         var tvValue: String = result?.text.toString()

            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("/")){

                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    //Checks for Operator
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

    private fun removeZeroAfterDot(result: String) : String{
        var value = result

        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }

        return value
    }
}