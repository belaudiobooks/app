package by.audiobooks.mob.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import by.audiobooks.mob.presentation.shared.SectionHeader

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SectionHeader(
            text = "Пошук"
        )
    }
}