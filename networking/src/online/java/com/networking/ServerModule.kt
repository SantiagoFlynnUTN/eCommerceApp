package com.networking

import com.networking.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerModule {

    private const val LOCALHOST = "10.0.2.2:8080"

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
            //.baseUrl("http://$LOCALHOST/")
            .baseUrl("https://api.mercadopago.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
