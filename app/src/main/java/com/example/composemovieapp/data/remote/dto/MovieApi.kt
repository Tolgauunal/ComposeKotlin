package com.example.composemovieapp.data.remote.dto

import com.example.composemovieapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(".")
    suspend fun getMovie(@Query("s") searchString: String,
        @Query("apikey") apiKey: String = Constants.API_KEY): MoviesDto

    @GET(".")
    suspend fun getMovieDetail(@Query("i") imdbId: String,
        @Query("apikey") apiKey: String = Constants.API_KEY): MoviesDetailDto
}