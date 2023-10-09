package com.example.composemovieapp.presentation.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.domain.use_case.get_movei.GetMovieUseCase
import com.example.composemovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state: State<MoviesState> = _state

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String) {
        getMovieUseCase.executeGetMovies(search = search).onEach {
            when (it) {
                is Resource.Error -> {
                    _state.value = MoviesState(error = it.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}