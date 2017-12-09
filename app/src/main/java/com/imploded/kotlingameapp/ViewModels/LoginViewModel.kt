package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.repository.LoginRepository

class LoginViewModel(private val updateUiFunc: (Boolean) -> Unit){

    private fun isValid(): Boolean = !userName.isEmpty() and !password.isEmpty()

    var userName: String = ""
        set(value) {
            field = value
            updateUiFunc(isValid())
        }
    var password: String = ""
        set(value) {
            field = value
            updateUiFunc(isValid())
        }

    fun login(loginCallbackFunc: (Boolean) -> Unit) {
        updateUiFunc(false)
        val repository = LoginRepository()
        repository.login(userName, password, loginCallbackFunc)
    }

}
