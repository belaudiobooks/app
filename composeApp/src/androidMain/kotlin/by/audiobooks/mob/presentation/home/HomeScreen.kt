package by.audiobooks.mob.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.presentation.ViewState
import by.audiobooks.mob.presentation.model.SectionContent
import by.audiobooks.mob.presentation.navigation.Book
import by.audiobooks.mob.presentation.shared.ErrorHeader
import by.audiobooks.mob.presentation.shared.NarrationPreview
import by.audiobooks.mob.presentation.shared.SectionHeader
import by.audiobooks.mob.presentation.shared.section.HorizontalSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeViewModel = koinViewModel()
) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Column(modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start) {
            val newNarrations =  homeScreenViewModel.newNarrations.collectAsState()
            val topCategories = homeScreenViewModel.topCategories.collectAsState()
            SectionHeader(text = "Навінкі")
            when (newNarrations.value.narrations) {
                is ViewState.Success -> {
                    LatestNarrations(
                        narrations = (newNarrations.value.narrations as ViewState.Success<List<BookCover>>).data,
                        navController = navController
                    )
                }
                else -> {
                    ErrorHeader("Loading")
                }
            }
            topCategories.value.entries.forEach { entry ->
                when (entry.value) {
                    is ViewState.Success -> {
                        HorizontalSection(
                            content = (entry.value as ViewState.Success<SectionContent>).data,
                            navController = navController,
                            limit = 10
                        )
                    }
                    else -> {
                        ErrorHeader("Loading")
                    }
                }
            }
            Spacer(modifier = Modifier.fillMaxWidth().requiredHeight(20.dp))
        }
    }
}

@Composable
fun LatestNarrations(
    narrations: List<BookCover>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
    ) {
        items(items = narrations) {
            NarrationPreview(
                item = it,
                onClick = {
                    navController.navigate(Book(bookUuid = it.uuid))
                }
            )
        }
    }
}
