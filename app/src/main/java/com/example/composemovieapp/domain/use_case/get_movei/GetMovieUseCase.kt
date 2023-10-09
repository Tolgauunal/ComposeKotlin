package com.example.composemovieapp.domain.use_case.get_movei

import com.example.composemovieapp.data.remote.dto.toMovieList
import com.example.composemovieapp.domain.model.Movie
import com.example.composemovieapp.domain.repository.MovieRepository
import com.example.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search = search)
            if (movieList.Response.equals("True")) {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error(message = "No movie"))
            }

        } catch (e: Exception) {
            emit(Resource.Error(message = "No internet connection"))

        }
    }

}