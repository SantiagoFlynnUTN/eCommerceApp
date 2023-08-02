package com.networking

import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import com.networking.api.Api
import com.networking.usecase.FeedUseCase
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

    @Provides
    @Singleton
    fun provideFeedUseCase(
        api: Api,
    ): IFeedUseCase {
        return FeedUseCase(api)
    }
}
