package com.networking.usecase

import com.domain.model.FeedItem
import com.domain.usecase.IFeedUseCase
import com.networking.api.Api
import com.networking.extractListResult
import com.networking.safeApiCall

class FeedUseCase(
    private val api: Api,
) : IFeedUseCase {

    override suspend fun invoke(): Result<List<FeedItem>> {
        return safeApiCall(
            { api.getFeed() },
            { extractListResult() },
        )
    }
}
