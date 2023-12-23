package com.networking.api

import com.networking.base.BaseResponse
import com.networking.model.CardTokenDto
import com.networking.model.ClientDto
import com.networking.model.FeedItemDto
import com.networking.model.TokenDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("feed")
    suspend fun getFeed(): Response<BaseResponse<List<FeedItemDto>>>

    @Headers("Authorization: Bearer TEST-1903895392578157-030718-b85bdfec403271ebc5876cb40829631c-130256072",
        "Content-Type: application/json")
    @POST("customers")
    suspend fun postNewClient(
        @Body body: ClientDto
    ): Response<BaseResponse<Unit>>

    @Headers("Authorization: Bearer TEST-1903895392578157-030718-b85bdfec403271ebc5876cb40829631c-130256072",
        "Content-Type: application/json")
    @GET("customers/{${"customerId"}}")
    suspend fun getClient(
        @Path("customerId") customerId: String,
    ): Response<BaseResponse<Unit>>

    @Headers("Authorization: Bearer TEST-1903895392578157-030718-b85bdfec403271ebc5876cb40829631c-130256072",
        "Content-Type: application/json")
    @POST("/card_tokens")
    suspend fun postCard(
        @Body body: CardTokenDto
    ): Response<BaseResponse<Unit>>

    @Headers("Authorization: Bearer TEST-1903895392578157-030718-b85bdfec403271ebc5876cb40829631c-130256072",
        "Content-Type: application/json")
    @POST("/customers/{${"customerId"}}/cards")
    suspend fun saveCardToClient(
        @Path("customerId") customerId: String,
        @Body cardToken: TokenDto
    ): Response<BaseResponse<Unit>>

    companion object {
        fun init(client: Retrofit): Api {
            return client.create(Api::class.java)
        }
    }
}

/*
CUIT
20392930427

* deb
* 4517690107650729
* 642
* 8,
    "expiration_year": 2028
* */

// 4546420029431049
// 584
// 02/27
