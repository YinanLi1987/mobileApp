package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    var heightInput by  mutableStateOf("")
    var weightInput by  mutableStateOf("")


    var bmi :Float=0.0f
        get() {
            return calculate()
        }


    private var height:Float=0.0f
        get() {
            return heightInput.toFloatOrNull() ?: 0.0f
        }
    private var weight:Int=0
        get() {
            return weightInput.toIntOrNull() ?: 0
        }
    fun changeHeightInput(value:String){
        heightInput=value
    }
    fun changeWeightInput(value: String){
        weightInput=value
    }

    private fun calculate():Float {
        return if ( weight >0 && height >0 ) weight /(height*height) else 0.0f
    }

}


