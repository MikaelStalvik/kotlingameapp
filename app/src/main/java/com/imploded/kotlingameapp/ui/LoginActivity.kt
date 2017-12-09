package com.imploded.kotlingameapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.viewmodels.LoginViewModel
import com.imploded.kotlingameapp.utils.afterTextChanged
import com.imploded.kotlingameapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private fun updateView(isValid: Boolean) {
        loginButton.isEnabled = isValid
    }

    private val viewModel: LoginViewModel = LoginViewModel{updateView(it)}

    private val checkLoginStatus = { status: Boolean ->
        runOnUiThread {
            if (status) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                loginButton.isEnabled = true
                toast(getString(R.string.failedToLogin))
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userNameEditText.afterTextChanged { viewModel.userName = it }
        passwordEditText.afterTextChanged { viewModel.password = it }
        userNameEditText.setText("mikael")
        passwordEditText.setText("12345")

        loginButton.setOnClickListener {
            viewModel.login{checkLoginStatus(it)}
        }
    }
}
