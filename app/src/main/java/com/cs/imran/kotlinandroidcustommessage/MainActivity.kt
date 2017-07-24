package com.cs.imran.kotlinandroidcustommessage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    public fun showMessage(view: View){
        val m = CustomMessage(this, 5000, Gravity.TOP)
        m.show("Hello this is a custom toast")
    }
}
