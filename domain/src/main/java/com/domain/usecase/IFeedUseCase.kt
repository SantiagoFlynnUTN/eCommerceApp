package com.domain.usecase

import com.domain.model.FeedItem

interface IFeedUseCase {
    suspend operator fun invoke(): Result<List<FeedItem?>>
}
