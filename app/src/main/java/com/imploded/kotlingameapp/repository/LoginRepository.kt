package com.imploded.kotlingameapp.repository

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.success
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.asJson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by Mikael on 2017-11-29.
 */
class LoginRepository {
    /*
    fun Login(userName: String, password: String, loginStatus: OnLoginStatus) : Boolean {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)
        val json = request.asJson
        Log.d("json", json)

        val req = url.httpPost().body(json)
        req.httpHeaders["Content-Type"] = "application/json"
        req.response { request, response, result ->
            val success = response.httpStatusCode == 200
            loginStatus(success)
            Log.d("res", result.toString())
        }

        return true
    }*/
    fun login(userName: String, password: String, loginStatus: OnLoginStatus) {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)
        val json = request.asJson
        Log.d("json", json)

        var loginSuccess = false;
        val req = url.httpPost().body(json)
        req.httpHeaders["Content-Type"] = "application/json"
        doAsync {
            req.response { _, response, _ ->
                loginSuccess = response.httpStatusCode == 200
                loginStatus(loginSuccess)
            }
        }
    }
}

interface OnLoginStatus{
    operator fun invoke(loginOk: Boolean)
}