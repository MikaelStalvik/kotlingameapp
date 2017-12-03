package com.imploded.kotlingameapp.ViewModels

import android.util.Log
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
            Log.d("ViewModel", "username:$value, f:$field, p:$userName")
        }
    var password: String = ""
        set(value) {
            field = value
            updateUiCallback.updateUi(isValid())
            Log.d("ViewModel", "password:$value, f:$field, p:$password")
        }

    fun login(loginCallback: OnLoginCallback) {
        val repository = LoginRepository()

//        repository.login(userName, password, object: OnLoginStatus {
//            override fun invoke(loginOk: Boolean) {
//                loginCallback(loginOk)
//            }
//        })
        repository.login(userName, password, loginCallback)
    }

}

//interface OnUpdateUiCallback {
//    fun updateUi(isValid: Boolean)
//    //operator fun invoke(isValid: Boolean)
//}