package com.imploded.kotlingameapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.utils.fromJson

class FilterActivity : AppCompatActivity() {

    companion object {
        val FilterPlatformId = "FilterPlatformId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        var json = intent.getStringExtra(FilterPlatformId)
        val platforms = Gson().fromJson<Map<String, Int>>(json)
        val k = 13;
    }
}
