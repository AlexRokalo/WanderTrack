package com.ar.wandertrack.presentation.ui.authorization.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.wandertrack.domain.repository.AuthRepository
import com.ar.wandertrack.domain.usecase.SignInWithCredentialManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authRepository: AuthRepository,
    private val credentialManagerUseCase: SignInWithCredentialManagerUseCase
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError

    init {
        Log.d("LoginViewModel", "Init")
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

    fun signInWithCredential() {
        viewModelScope.launch {
            val user = credentialManagerUseCase()
            if (user.isSuccess) {
                Log.d("LoginViewModel", "User = $user")
            } else {
                Log.d("LoginViewModel", "User = $user")
            }
        }
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