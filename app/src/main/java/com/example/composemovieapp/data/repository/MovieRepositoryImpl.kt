package com.example.composemovieapp.data.repository

import com.example.composemovieapp.data.remote.dto.MovieApi
import com.example.composemovieapp.data.remote.dto.MoviesDetailDto
import com.example.composemovieapp.data.remote.dto.MoviesDto
import com.example.composemovieapp.domain.repository.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovie(search)
    }

    override suspend fun getMoviesDetail(imdbId: String): MoviesDetailDto {
        return api.getMovieDetail(imdbId)
    }
}