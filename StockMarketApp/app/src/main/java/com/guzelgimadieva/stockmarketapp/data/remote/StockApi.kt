package com.guzelgimadieva.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apiKey") apiKey: String
    ): ResponseBody

    companion object {
        const val API_KEY = "O1B7YD62KKZS58U8"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}