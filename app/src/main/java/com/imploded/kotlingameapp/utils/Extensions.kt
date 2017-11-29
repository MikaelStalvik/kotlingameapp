package com.imploded.kotlingameapp.utils

import com.google.gson.GsonBuilder

/**
 * Created by Mikael on 2017-11-29.
*/
val Any.asJson: String
    get() {
        val gson = GsonBuilder().create()
        val json = gson.toJson(this)
        return json.toString()
    };

fun Any.asJson(pretty: Boolean): String {

    val gson = if (pretty) GsonBuilder().setPrettyPrinting().create() else GsonBuilder().create()
    val json = gson.toJson(this)
    return json.toString()
};
