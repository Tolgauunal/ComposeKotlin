package com.example.composemovieapp.presentation.movie

sealed class MoviesEvent {
    data class Search(val searchString: String) : MoviesEvent()
}