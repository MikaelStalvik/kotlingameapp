package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import org.jetbrains.anko.doAsync

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

    fun login(updateLoginStatusFunc: (Boolean) -> Unit) {
        updateUiFunc(false)
        doAsync {
            val okResult = repository.login(userName, password)
            updateLoginStatusFunc(okResult)
        }
    }

}
