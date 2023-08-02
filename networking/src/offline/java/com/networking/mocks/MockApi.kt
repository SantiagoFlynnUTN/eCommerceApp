package com.networking.mocks

import com.google.gson.reflect.TypeToken
import com.mocks.MockProvider
import com.mocks.MockProvider.Companion.FEED_ITEMS_MOCK_FILE
import com.networking.api.Api
import com.networking.base.BaseResponse
import com.networking.model.FeedItemDto
import retrofit2.Response
import java.lang.reflect.Type

class MockApi(private val mockProvider: MockProvider) : Api {
    private inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

    override suspend fun getFeed(): Response<BaseResponse<List<FeedItemDto>>> {
        return mockProvider.getSomething(typeToken<BaseResponse<List<FeedItemDto>>>(), FEED_ITEMS_MOCK_FILE)
    }
}
