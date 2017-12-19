package com.imploded.kotlingameapp.repository

import com.github.kittinunf.fuel.httpPost
import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import com.imploded.kotlingameapp.model.LoginRequest
import com.imploded.kotlingameapp.utils.AppConstants
import com.imploded.kotlingameapp.utils.asJson


class LoginRepository : LoginRepositoryInterface {

    override fun login(username: String, password: String): Boolean {
        val url = AppConstants.LoginUrl
        val requestData = LoginRequest(username, password)

        val headers = mapOf("Content-Type" to "application/json")
        val (_, response, _) = url.httpPost().body(requestData.asJson).header(headers).responseString()
        return response.httpStatusCode == 200
    }
}
