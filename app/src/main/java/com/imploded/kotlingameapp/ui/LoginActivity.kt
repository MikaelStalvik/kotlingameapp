package com.imploded.kotlingameapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.ViewModels.LoginViewModel
import com.imploded.kotlingameapp.interfaces.OnLoginCallback
import com.imploded.kotlingameapp.interfaces.OnUpdateUiCallback
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.model.GameListResult
import com.imploded.kotlingameapp.utils.afterTextChanged
import com.imploded.kotlingameapp.utils.fromJson
import com.imploded.kotlingameapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    /*
    private val viewModel: LoginViewModel = LoginViewModel(object: onUpdateUi {
        override fun invoke(isValid: Boolean) {
            loginButton.isEnabled = isValid
        }
    })
    */
    fun updateView(isValid: Boolean) {
        loginButton.isEnabled = isValid
    }

    private val viewModel: LoginViewModel = LoginViewModel(object: OnUpdateUiCallback {
        override fun updateUi(valid: Boolean) = updateView(valid)
    })
    /*
    private val viewModel: LoginViewModel = LoginViewModel(object: OnUpdateUiCallback {
        override fun invoke(valid: Boolean) = updateView(valid)
    })
    */

    val checkLoginStatus = { status: Boolean ->
        runOnUiThread {
            if (status) {
                val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("key", value)
                startActivity(intent)

                //toast("Good to go!")
            }
            else toast("Failed!!")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userNameEditText.afterTextChanged { viewModel.userName = it }
        passwordEditText.afterTextChanged { viewModel.password = it }
        userNameEditText.setText("mikael")
        passwordEditText.setText("12345")
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
            viewModel.login(object: OnLoginCallback {
                override fun invoke(valid: Boolean) = checkLoginStatus(valid)
            })



            /*
            viewModel.login(object: OnLoginStatus{
                override fun invoke(loginOk: Boolean) {
                    runOnUiThread {
                        if (loginOk) toast("Good to go!") else toast("Failed!!")
                    }
                }
            })
            */

            //if (viewModel.login()) toast("Good to go!") else toast("Failed!!")
            /*
            val repository = LoginRepository()
            repository.Login(userNameEditText.text.toString(), passwordEditText.text.toString(), object: OnLoginStatus {
                override fun invoke(loginOk: Boolean) {
                    if (!loginOk) toast("Could not login!") else {
                        toast("Will show main activity")
                        //startActivity<MainActivity>(MainActivity.ID to it.id)
                    }
                }
            })*/
        }
    }
}
