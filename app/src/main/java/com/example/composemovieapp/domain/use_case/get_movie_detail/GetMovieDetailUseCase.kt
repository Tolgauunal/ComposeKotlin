package com.example.composemovieapp.domain.use_case.get_movie_detail

import com.example.composemovieapp.data.remote.dto.toMovieDetail
import com.example.composemovieapp.domain.model.MovieDetail
import com.example.composemovieapp.domain.repository.MovieRepository
import com.example.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMoviesDetail(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMoviesDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))

        } catch (e: Exception) {
            emit(Resource.Error(message = "No internet connection"))

        }
    }
}