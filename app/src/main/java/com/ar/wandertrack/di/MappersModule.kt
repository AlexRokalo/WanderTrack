package com.ar.wandertrack.di

import com.ar.wandertrack.data.model.UserEntity
import com.ar.wandertrack.data.model.UserMapper
import com.ar.wandertrack.data.model.mapper.Mapper
import com.ar.wandertrack.domain.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideUserMapper(): Mapper<UserEntity, User> = UserMapper()
}