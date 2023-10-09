package com.example.composemovieapp.domain.repository

import com.example.composemovieapp.data.remote.dto.MoviesDetailDto
import com.example.composemovieapp.data.remote.dto.MoviesDto
import com.example.composemovieapp.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMoviesDetail(imdbId: String): MoviesDetailDto

}