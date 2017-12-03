package com.imploded.kotlingameapp.interfaces

/**
 * Created by l19548726 on 2017-12-01.
 */
interface OnLoginCallback {
    operator fun invoke(valid: Boolean) // omit function name
}
