package com.imploded.kotlingameapp.interfaces

interface LoginStatusListener {
    operator fun invoke(status: Boolean)
}