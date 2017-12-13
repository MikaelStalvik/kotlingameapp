package com.imploded.kotlingameapp.repository

import com.github.kittinunf.fuel.httpPost
import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import com.imploded.kotlingameapp.interfaces.LoginStatusListener
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.AppConstants
import com.imploded.kotlingameapp.utils.asJson
import org.jetbrains.anko.doAsync


class LoginRepository : LoginRepositoryInterface {
    override fun test(hej: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(userName: String, password: String, listener: LoginStatusListener) {
        val url = AppConstants.LoginUrl
        val request = LoginRequest(userName, password)

        val req = url.httpPost().body(request.asJson)
        req.httpHeaders["Content-Type"] = "application/json"
        doAsync {
            req.response { _, response, _ ->
                listener(true)
                //loginCallback(response.httpStatusCode == 200)
            }
        }
    }
}
