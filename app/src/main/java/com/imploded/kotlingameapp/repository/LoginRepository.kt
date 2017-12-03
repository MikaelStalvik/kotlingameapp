package com.imploded.kotlingameapp.repository

import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.imploded.kotlingameapp.interfaces.OnLoginCallback
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.asJson
import org.jetbrains.anko.doAsync

/**
 * Created by Mikael on 2017-11-29.
 */
class LoginRepository {
    /*
    fun Login(userName: String, password: String, loginCallback: OnLoginStatus) : Boolean {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)
        val json = request.asJson
        Log.d("json", json)

        val req = url.httpPost().body(json)
        req.httpHeaders["Content-Type"] = "application/json"
        req.response { request, response, result ->
            val success = response.httpStatusCode == 200
            loginCallback(success)
            Log.d("res", result.toString())
        }

        return true
    }*/
    fun login(userName: String, password: String, loginCallback: OnLoginCallback) {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)
        val json = request.asJson
        Log.d("json", json)

        val req = url.httpPost().body(json)
        req.httpHeaders["Content-Type"] = "application/json"
        doAsync {
            req.response { _, response, _ ->
                //loginCallback.updateLoginStatus(response.httpStatusCode == 200)
                loginCallback(response.httpStatusCode == 200)
            }
        }
    }
}

//interface OnLoginStatus{
//    operator fun invoke(loginOk: Boolean)
//}