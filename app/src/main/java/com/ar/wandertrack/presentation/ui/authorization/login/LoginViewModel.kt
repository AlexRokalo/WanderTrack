package com.ar.wandertrack.presentation.ui.authorization.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        _emailError.value = !isValidEmail(newEmail)
    }

    fun login() {
        Log.d("LoginViewModel", "Login email = ${email.value}")
    }

    private fun isValidEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            true
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

}