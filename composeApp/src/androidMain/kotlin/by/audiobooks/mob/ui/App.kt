package by.audiobooks.mob.ui

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.presentation.HomeScreenUiState
import by.audiobooks.mob.presentation.HomeViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(homeScreenViewModel: HomeViewModel = koinViewModel()) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val greeting = remember { Greeting().greet() }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val st =  homeScreenViewModel.uiState.collectAsState()
            when (st.value) {
                is HomeScreenUiState.Success -> {
                    Text("!!")
                    BooksList((st.value as HomeScreenUiState.Success).books)
                    Text("!!!")
                }
                else -> {
                    Text("??????")
                }
            }
            Button(onClick = {
                homeScreenViewModel.reloadData()
                showContent = !showContent
            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Compose: $greeting")
                }
            }
        }
    }
}


@Composable
fun BooksList(books: List<Book>) {
    Text("${books.size}")
    LazyColumn {
        itemsIndexed(books) { i, book ->
            Text("${i} - ${book.title}")
        }
    }
}