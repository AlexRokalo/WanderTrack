package com.ar.wandertrack.domain.usecase

import android.content.Context
import com.ar.wandertrack.domain.model.User
import com.ar.wandertrack.domain.repository.AuthRepository

class SignInWithCredentialManagerUseCase(
    private val authRepository: AuthRepository,
    private val context: Context
) {
    suspend operator fun invoke(): Result<User> {
        return authRepository.signInWithCredentialManager(context)
    }
}