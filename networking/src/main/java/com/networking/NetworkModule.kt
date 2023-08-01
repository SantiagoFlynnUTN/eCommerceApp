package com.networking

import android.content.Context
import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import com.google.gson.Gson
import com.networking.api.Api
import com.networking.mocks.MockApi
import com.networking.usecase.FeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val LOCALHOST = "10.0.2.2:8080"

    @Provides
    @Singleton
    @Named("hiltNetwork")
    fun provideTest(): HiltTestInterface {
        return HiltTestImpl()
    }

    @Provides
    @Singleton
    @Named("MockApi")
    fun providesMockApi(
        @ApplicationContext context: Context,
    ): Api {
        return MockApi(context, Gson())
    }

    @Provides
    @Singleton
    @Named("ServerApi")
    fun providesApi(
        client: Retrofit,
    ): Api {
        return Api.init(client)
    }

    @Provides
    @Singleton
    fun provideGsonClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://$LOCALHOST")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFeedUseCase(
        @Named("MockApi") api: Api,
    ): IFeedUseCase {
        return FeedUseCase(api)
    }
}
