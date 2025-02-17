package by.audiobooks.mob.presentation.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.audiobooks.mob.presentation.ViewState
import by.audiobooks.mob.presentation.model.SectionContent
import by.audiobooks.mob.presentation.shared.section.VerticalSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryScreen(
    tagId: Long,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = koinViewModel()
) {
    Column(modifier
        .fillMaxWidth()
    ) {
        categoryViewModel.getCatalogContent(tagId)
        val sectionContent =  categoryViewModel.sectionContent.collectAsState()
        when (sectionContent.value) {
            is ViewState.Success -> {
                Category(
                    (sectionContent.value as ViewState.Success<SectionContent>).data,
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
fun Category(
    sectionContent: SectionContent,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    VerticalSection(content = sectionContent, navController = navHostController, limit = 0)
}
