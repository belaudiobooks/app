package by.audiobooks.mob.presentation.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.audiobooks.mob.presentation.ViewState
import by.audiobooks.mob.presentation.model.SectionContent
import by.audiobooks.mob.presentation.shared.section.HorizontalSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    catalogViewModel: CatalogViewModel = koinViewModel()
) {
    Column(modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sectionsContent =  catalogViewModel.sectionsContent.collectAsState()
        when (sectionsContent.value) {
            is ViewState.Success -> {
                Categories(
                    (sectionsContent.value as ViewState.Success<List<SectionContent>>).data,
                    navHostController)
            }
            else -> {
                Text("Loading")
            }
        }
    }
    Spacer(modifier = Modifier.fillMaxWidth().requiredHeight(20.dp))
}

@Composable
fun Categories(
    sectionsContent: List<SectionContent>,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState
    ) {
        items(items = sectionsContent) {
            HorizontalSection(content = it, navController = navHostController, limit = 10)
        }
    }
}
