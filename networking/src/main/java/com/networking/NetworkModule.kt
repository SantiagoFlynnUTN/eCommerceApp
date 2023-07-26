package com.networking

import com.domain.HiltTestInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("hiltNetwork")
    fun provideTest(): HiltTestInterface {
        return HiltTestImpl()
    }
}