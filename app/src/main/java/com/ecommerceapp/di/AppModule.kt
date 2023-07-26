package com.ecommerceapp.di

import com.domain.HiltTestInterface
import com.ecommerceapp.HiltTestClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("hiltAppModule")
    fun provideTest(
        str: String,
    ): HiltTestInterface {
        return HiltTestClass(str)
    }

    @Singleton
    @Provides
    fun provideString(): String {
        return "App Module"
    }
}
