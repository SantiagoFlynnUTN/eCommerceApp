package com.networking.usecase

import com.domain.model.FeedItem
import com.domain.usecase.IFeedUseCase
import com.networking.api.Api

class FeedUseCase(
    private val api: Api
) : IFeedUseCase {
    override suspend fun invoke(): Result<List<FeedItem?>> {
        val result = api.getFeed()
        val status = result.isSuccessful
        val feed = result.body()

        return if (status && !feed.isNullOrEmpty()) {
            Result.success(feed.map { it?.toDomainModel() })
        } else {
            Result.failure(Exception("Not found"))
        }
    }
}
