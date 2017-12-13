package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import com.imploded.kotlingameapp.interfaces.LoginStatusListener

class LoginViewModel(private val repository: LoginRepositoryInterface, private val updateUiFunc: (Boolean) -> Unit){

    fun isValid(): Boolean = !userName.isEmpty() and !password.isEmpty()

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

    fun login(loginStatusListener: LoginStatusListener) {
        updateUiFunc(false)
        repository.login(userName, password, loginStatusListener)
    }

}
