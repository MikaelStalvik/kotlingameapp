package com.imploded.kotlingameapp.repository

/**
 * Created by Mikael on 2017-11-29.
 */
public interface Command<out T> {
    fun execute(): T
}