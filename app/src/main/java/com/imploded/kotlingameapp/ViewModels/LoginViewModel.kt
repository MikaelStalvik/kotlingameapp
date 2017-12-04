package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.interfaces.OnLoginCallback
import com.imploded.kotlingameapp.interfaces.OnUpdateUiCallback
import com.imploded.kotlingameapp.repository.LoginRepository

/**
 * Created by l19548726 on 2017-11-30.
 */
class LoginViewModel(private val updateUiCallback: OnUpdateUiCallback){

    private fun isValid(): Boolean = !userName.isEmpty() and !password.isEmpty()

    var userName: String = ""
        set(value) {
            field = value
            updateUiCallback.updateUi(isValid())
        }
    var password: String = ""
        set(value) {
            field = value
            updateUiCallback.updateUi(isValid())
        }

    fun login(loginCallback: OnLoginCallback) {
        val repository = LoginRepository()
        repository.login(userName, password, loginCallback)
    }

}
