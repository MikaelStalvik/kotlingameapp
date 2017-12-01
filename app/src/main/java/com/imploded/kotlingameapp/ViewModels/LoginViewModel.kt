package com.imploded.kotlingameapp.ViewModels

import android.util.Log
import com.imploded.kotlingameapp.repository.LoginRepository
import com.imploded.kotlingameapp.repository.OnLoginStatus
import com.imploded.kotlingameapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty

/**
 * Created by l19548726 on 2017-11-30.
 */
class LoginViewModel(private val updateUiListener: UpdateUiListener){

    fun isValid(): Boolean = !userName.isNullOrEmpty() and !password.isNullOrEmpty()

    var userName: String = ""
        set(value) {
            field = value
            updateUiListener.updateUi(isValid())
            Log.d("ViewModel", "username:" + value + ", f:" + field + ", p:" + userName )
        }
    var password: String = ""
        set(value) {
            field = value
            updateUiListener.updateUi(isValid())
            Log.d("ViewModel", "password:" + value + ", f:" + field + ", p:" + password)
        }

    fun login(loginStatus: OnLoginStatus) {
        val repository = LoginRepository()

//        repository.login(userName, password, object: OnLoginStatus {
//            override fun invoke(loginOk: Boolean) {
//                loginStatus(loginOk)
//            }
//        })
        repository.login(userName, password, loginStatus)
    }

}

interface UpdateUiListener {
    fun updateUi(isValid: Boolean)
    //operator fun invoke(isValid: Boolean)
}