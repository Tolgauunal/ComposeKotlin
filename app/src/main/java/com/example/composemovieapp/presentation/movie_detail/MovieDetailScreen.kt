package com.example.composemovieapp.presentation.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val openAlertDialog = remember { mutableStateOf(false) }
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Center) {
        state.movie?.let {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally) {
                Image(painter = rememberAsyncImagePainter(model = it.Poster),
                    contentDescription = it.Title,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(300.dp, 300.dp)
                        .clip(RectangleShape)
                        .align(CenterHorizontally)
                        .clickable { openAlertDialog.value = true })

                Text(text = it.Title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(14.dp)
                        .clickable {
                            setSnackBarState(!snackbarVisibleState)
                        },
                    color = Color.White)

                Text(text = it.Year,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.Actors,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.Country,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = "Director: ${it.Director}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = "IMDB Rating: ${it.imdbRating}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

            }
        }

        if (state.error.isNotBlank()) {
            Text(text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Center))
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
        if (openAlertDialog.value) {
            state.movie?.let {
                when {
                    openAlertDialog.value -> {
                        customAlertDialog(onDismissRequest = { openAlertDialog.value = false },
                            onConfirmation = { openAlertDialog.value = false },
                            dialogTitle = it.Title,
                            dialogText = it.Country,
                            icon = Icons.Default.AccountBox,
                            image = rememberAsyncImagePainter(model = it.Poster))
                    }
                }
            }
        }
        if (snackbarVisibleState) {
            Snackbar(action = {
                Button(onClick = {}) {
                    Text("MyAction")
                }
            }, modifier = Modifier.padding(8.dp).align(Alignment.BottomCenter)) { Text(text = state.movie?.Year.toString()) }
        }
    }
}


@Composable
fun customAlertDialog(onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    image: AsyncImagePainter) {
    AlertDialog(
        onDismissRequest = { onDismissRequest },
        confirmButton = { onConfirmation },
        title = { Text(text = dialogTitle) },
        icon = { Icon(icon, contentDescription = null) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(painter = image,
                    contentDescription = null,
                    modifier = Modifier.align(CenterHorizontally))
                Text(text = dialogText, modifier = Modifier.align(CenterHorizontally))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "Dismiss")
            }
        },
    )
}

