package com.example.composemovieapp.presentation.movie

import com.example.composemovieapp.domain.model.Movie

data class MoviesState(
    val isLoading:Boolean =false,
    val movies:List<Movie> = emptyList(),
    val error: String="",
    val search:String="batman"
)