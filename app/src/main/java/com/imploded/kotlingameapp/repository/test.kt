package com.imploded.kotlingameapp.repository

import android.util.Log

/**
 * Created by Mikael on 2017-11-30.
 */
interface Callback {
    fun updateUi()
}

class CallbackImpl : Callback {
    override fun updateUi() {
        Log.d("Hej", "I have callbacked")
    }
}

class master {
    fun register(callback: Callback) {
        callback.updateUi()
    }

    fun doCall() {
        val callback = CallbackImpl()
        register(callback)
    }
}
