package com.networking

import android.content.Context
import com.google.gson.Gson
import com.mocks.MockProvider
import com.networking.api.Api
import com.networking.mocks.MockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MocksModule {

    @Provides
    @Singleton
    fun providesMockApi(
        mockProvider: MockProvider,
    ): Api {
        return MockApi(mockProvider)
    }

    @Provides
    @Singleton
    fun providesMockProvider(
        @ApplicationContext context: Context,
    ): MockProvider {
        return MockProvider(context, Gson())
    }
}
