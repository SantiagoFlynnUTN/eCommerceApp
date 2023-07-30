package com.networking.api

import com.networking.model.FeedItemDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface Api {

    @GET("/feed")
    suspend fun getFeed(): Response<List<FeedItemDto?>>

    companion object {
        fun init(client: Retrofit): Api {
            return client.create(Api::class.java)
        }
    }
}
