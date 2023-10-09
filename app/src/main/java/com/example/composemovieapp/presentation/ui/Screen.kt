package com.example.composemovieapp.presentation.ui

sealed class Screen(val route:String){
    object MovieScreen:Screen("movie_screen")
    object MovieDetailScreen:Screen("movie_detail_screen")
}
