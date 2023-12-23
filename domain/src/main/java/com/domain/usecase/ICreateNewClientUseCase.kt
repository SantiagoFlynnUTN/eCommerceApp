package com.domain.usecase

import com.domain.model.FeedItem

interface ICreateNewClientUseCase {
    suspend operator fun invoke(): Unit?
}
