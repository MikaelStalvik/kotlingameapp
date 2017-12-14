package com.imploded.kotlingameapp

import com.imploded.kotlingameapp.interfaces.LoginRepositoryInterface
import com.imploded.kotlingameapp.viewmodels.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.lang.Thread.sleep


/* A bit too many tests by purpose... */
class LoginUnitTest {
    private lateinit var repo: LoginRepositoryInterface
    private lateinit var viewModel: LoginViewModel

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


    @Test
    fun whenLoginFails() {
        var b = false
        viewModel.userName = "kalle"
        viewModel.password = "123"
        `when`(repo.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(false)
        viewModel.login {
            status -> b = status
        }
        sleep(500) // ugly roundtrip to await the async call
        assertFalse(b)
    }
    @Test
    fun whenLoginSucceeds() {
        var b = false
        viewModel.userName = "kalle"
        viewModel.password = "123"
        `when`(repo.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true)
        viewModel.login {
            status -> b = status
        }
        sleep(500) // ugly roundtrip to await the async call
        assertTrue(b)
    }

}
