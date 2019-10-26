package com.cnm.naverhomework.network

import com.cnm.naverhomework.network.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {
    @GET("v1/search/movie.json")
    fun getNaverMovie(
        @Query("query") query: String
    ) : Call<NaverMovieResponse>
}