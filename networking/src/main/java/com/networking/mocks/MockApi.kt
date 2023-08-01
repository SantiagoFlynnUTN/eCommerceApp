package com.networking.mocks

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.networking.api.Api
import com.networking.base.BaseResponse
import com.networking.model.FeedItemDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.reflect.Type

class MockApi(private val context: Context, private val gson: Gson) : Api {

    private inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

    private suspend fun <T> getMockResponse(fileName: String, type: Type): T? = withContext(Dispatchers.IO) {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        gson.fromJson(json, type)
    }

    private suspend fun <T> getSomething(type: Type, fileName: String): Response<T> {
        val mockResponse: T? = getMockResponse(fileName, type)
        return Response.success(mockResponse)
    }

    override suspend fun getFeed(): Response<BaseResponse<List<FeedItemDto>>> {
        return getSomething(typeToken<BaseResponse<List<FeedItemDto>>>(), "mock_feed_data.json")
    }
}
