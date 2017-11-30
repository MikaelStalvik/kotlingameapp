package com.imploded.kotlingameapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.gson.GsonBuilder

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