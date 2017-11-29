package com.imploded.kotlingameapp.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.repository.LoginRepository
import org.jetbrains.anko.doAsync
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val repository = LoginRepository()
            repository.Login(userNameEditText.text.toString(), passwordEditText.text.toString())
        }
    }
}
