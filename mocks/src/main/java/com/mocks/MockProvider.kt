package com.mocks

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.reflect.Type
import java.net.HttpURLConnection.HTTP_NOT_FOUND

class MockProvider(
    private val context: Context,
    private val gson: Gson,
) {

    companion object {
        const val FEED_ITEMS_MOCK_FILE = "mock_feed_data.json"
    }

    private suspend fun <T> getMockResponse(fileName: String, type: Type): T? = withContext(
        Dispatchers.IO,
    ) {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        gson.fromJson(json, type)
    }

    suspend fun <T> getSomething(type: Type, fileName: String): Response<T> {
        val mockResponse: T? = getMockResponse(fileName, type)
        return if (mockResponse != null) {
            Response.success(mockResponse)
        } else {
            val errorCode = HTTP_NOT_FOUND
            val errorBody = ResponseBody.create(null, "Error fetching json mock")
            Response.error(errorCode, errorBody)
        }
    }
}
