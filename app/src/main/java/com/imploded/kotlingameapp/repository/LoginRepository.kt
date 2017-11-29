package com.imploded.kotlingameapp.repository

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.asJson
import java.net.URL

/**
 * Created by Mikael on 2017-11-29.
 */
class LoginRepository {

    fun Login(userName: String, password: String) : Boolean {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)
        val json = request.asJson
        Log.d("json", json)
        Fuel.post(url).body(request.asJson).response{request, response, result ->
            Log.d("res", result.toString())
        }

        return true
    }
}