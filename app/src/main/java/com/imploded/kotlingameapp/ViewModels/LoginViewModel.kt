package com.imploded.kotlingameapp.ViewModels

import android.util.Log
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty

/**
 * Created by l19548726 on 2017-11-30.
 */
class LoginViewModel{

    var onDataUpdated : OnUpdateUi = object

    fun isValid() : Boolean {
        val valid = !userName.isNullOrEmpty() and !password.isNullOrEmpty()
        onDataUpdated.updateUi()
        return valid
    }

    var userName: String by Delegates.observable("") {
        _, _, new -> Log.d("LoginViewModel", "userName set:" + new)
    }
    var password: String by Delegates.observable("") {
        _, _, new -> Log.d("LoginViewModel", "password set:" + new)
    }
}

interface OnUpdateUi {
    fun updateUi()
}