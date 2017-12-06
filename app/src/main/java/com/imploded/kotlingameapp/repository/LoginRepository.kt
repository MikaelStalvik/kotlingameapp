package com.imploded.kotlingameapp.repository

import com.github.kittinunf.fuel.httpPost
import com.imploded.kotlingameapp.interfaces.OnLoginCallback
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.asJson
import org.jetbrains.anko.doAsync

/**
 * Created by Mikael on 2017-11-29.
 */
class LoginRepository {

    fun login(userName: String, password: String, loginCallback: OnLoginCallback) {
        val url = "http://kotlinserver.azurewebsites.net/login"
        val request = LoginRequest(userName, password)

        val req = url.httpPost().body(request.asJson)
        req.httpHeaders["Content-Type"] = "application/json"
        doAsync {
            req.response { _, response, _ ->
                loginCallback(response.httpStatusCode == 200)
            }
        }
    }
}
