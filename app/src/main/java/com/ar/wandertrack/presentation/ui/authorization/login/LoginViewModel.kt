package com.ar.wandertrack.presentation.ui.authorization.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.wandertrack.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authRepository: AuthRepository
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError

    init {
        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            Log.d(
                "LoginViewModel",
                "User isFailure = ${user.isFailure}, isSuccess = ${user.isSuccess}"
            )
            Log.d("LoginViewModel", "User = $user")
        }
    }

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