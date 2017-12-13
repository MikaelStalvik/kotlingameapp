package com.imploded.kotlingameapp.interfaces

interface LoginRepositoryInterface{
    fun login(username: String, password: String, listener: LoginStatusListener)
    fun test(hej: String)
}