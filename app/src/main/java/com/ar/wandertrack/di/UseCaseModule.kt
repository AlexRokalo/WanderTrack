package com.ar.wandertrack.di

import android.content.Context
import com.ar.wandertrack.domain.repository.AuthRepository
import com.ar.wandertrack.domain.usecase.SignInWithCredentialManagerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(
        authRepository: AuthRepository,
        @ApplicationContext context: Context
    ): SignInWithCredentialManagerUseCase {
        return SignInWithCredentialManagerUseCase(authRepository, context)
    }
}