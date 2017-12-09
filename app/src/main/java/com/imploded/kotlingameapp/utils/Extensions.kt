package com.imploded.kotlingameapp.utils

import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

/**
 * Created by Mikael on 2017-11-29.
*/
val Any.asJson: String
    get() {
        val gson = GsonBuilder().create()
        val json = gson.toJson(this)
        return json.toString()
    }

fun Any.asJson(pretty: Boolean): String {

    val gson = if (pretty) GsonBuilder().setPrettyPrinting().create() else GsonBuilder().create()
    val json = gson.toJson(this)
    return json.toString()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

fun ImageView.load(url: String) {
    Picasso.with(context).load(url).into(this)
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}


