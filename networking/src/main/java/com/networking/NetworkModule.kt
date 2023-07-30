package com.networking

import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import com.networking.api.Api
import com.networking.usecase.FeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        api: Api,
    ): IFeedUseCase {
        return FeedUseCase(api)
    }
}
