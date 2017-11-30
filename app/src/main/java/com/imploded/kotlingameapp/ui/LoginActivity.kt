package com.imploded.kotlingameapp.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.ViewModels.LoginViewModel
import com.imploded.kotlingameapp.repository.LoginRepository
import com.imploded.kotlingameapp.utils.afterTextChanged
import org.jetbrains.anko.doAsync
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.onDataUpdated = {

        }
        userNameEditText.afterTextChanged { viewModel.userName = it }
        passwordEditText.afterTextChanged { viewModel.password = it }
        /*
        userNameEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.userName = p0.toString()
            }
        })
        passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.password = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })*/


        loginButton.setOnClickListener {
            val repository = LoginRepository()
            repository.Login(userNameEditText.text.toString(), passwordEditText.text.toString())
        }
    }
}
