package com.example.composemovieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.example.composemovieapp.util.Constants.IMDB_ID
import com.example.composemovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase,
      savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(it: String) {
        getMovieDetailUseCase.executeGetMoviesDetail(it).onEach {
            println(it.data)
            when (it) {
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message.toString())
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}
