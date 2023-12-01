package com.mocks

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.reflect.Type

class MockProvider(
    private val context: Context,
    private val gson: Gson,
) {

    companion object {
        const val FEED_ITEMS_MOCK_FILE = "mock_feed_data.json"
        const val BURGERS_HOME_FEED = "mock_burgers_feed.json"
    }

    private suspend fun <T> getMockResponse(fileName: String, type: Type): T? = withContext(
        Dispatchers.IO,
    ) {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        gson.fromJson(json, type)
    }

    suspend fun <T> getSomething(type: Type, fileName: String): Response<T> {
        val mockResponse: T? = getMockResponse(fileName, type)
        return Response.success(mockResponse)
    }
}
