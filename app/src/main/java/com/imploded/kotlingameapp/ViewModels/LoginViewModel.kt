package com.imploded.kotlingameapp.ViewModels

import android.util.Log
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty

/**
 * Created by l19548726 on 2017-11-30.
 */
class LoginViewModel(private val updateUi: onUpdateUi){


    fun isValid(): Boolean = !userName.isNullOrEmpty() and !password.isNullOrEmpty()

    var userName: String = ""
        set(value) {
            field = value
            updateUi(isValid())
            Log.d("ViewModel", "username:" + value + ", f:" + field + ", p:" + userName )
        }
    var password: String = ""
        set(value) {
            field = value
            updateUi(isValid())
            Log.d("ViewModel", "password:" + value + ", f:" + field + ", p:" + password)
        }


}

interface onUpdateUi {
    operator fun invoke(isValid: Boolean)
}