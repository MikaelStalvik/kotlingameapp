package com.imploded.kotlingameapp

import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import com.imploded.kotlingameapp.interfaces.LoginStatusListener
import com.imploded.kotlingameapp.viewmodels.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*





/* A bit too many tests by purpose... */
class LoginUnitTest {
    lateinit var repo: LoginRepositoryInterface
    lateinit var viewModel: LoginViewModel

    @Before
    fun init() {
        repo = mock(LoginRepositoryInterface::class.java)
        viewModel = LoginViewModel(repo,{})
    }

    @Test
    fun whenLoginDetailsAreNotFilledEnsureIsValidIsNotTrue() {
        assertFalse(viewModel.isValid())
    }
    @Test
    fun whenOnlyUsernameIsFilledEnsureIsValidIsNotTrue() {
        viewModel.userName = "mikael"
        assertFalse(viewModel.isValid())
    }
    @Test
    fun whenOnlyPasswordIsFilledEnsureIsValidIsNotTrue() {
        viewModel.userName = "mikael"
        assertFalse(viewModel.isValid())
    }
    @Test
    fun whenAllDetailsAreFilledEnsureIsValidIsNotTrue() {
        viewModel.userName = "mikael"
        viewModel.password = "12345"
        assertTrue(viewModel.isValid())
    }

    open class hepp: LoginStatusListener {
        var loginState = false
        override fun invoke(status: Boolean) {
            loginState = status
        }
    }

    @Test
    fun tester() {
        val st = hepp()
        val stx = mock(hepp::class.java)

        doAnswer {
            //val design = Design()

            val callback = it.arguments[0] as LoginStatusListener
            callback.invoke(false)

            null // or you can type return@doAnswer null

        }.`when`(repo).login("", "", any(LoginStatusListener::class.java))



        viewModel.login(stx)


    }
    /*
    @Test
    fun whenLoginFails() {
        var res = false
        viewModel.userName = "kalle"
        viewModel.password = "123"

        doAnswer{

            val callback = it.arguments[0] as LoginStatusListener
            callback.invoke(true)

            null
        }.`when`(repo).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), any(LoginStatusListener::class.java))
        assertTrue(true)
    }*/
}
