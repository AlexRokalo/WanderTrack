package com.ar.wandertrack.di

import com.ar.wandertrack.data.model.UserEntity
import com.ar.wandertrack.data.model.mapper.Mapper
import com.ar.wandertrack.data.repository.AuthRepositoryImpl
import com.ar.wandertrack.domain.model.User
import com.ar.wandertrack.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        mapper: Mapper<UserEntity, User>
    ): AuthRepository = AuthRepositoryImpl(auth, mapper)
}