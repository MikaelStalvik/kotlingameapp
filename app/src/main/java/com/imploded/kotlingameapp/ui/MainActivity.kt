package com.imploded.kotlingameapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.imploded.kotlingameapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        val ID = "MainActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
