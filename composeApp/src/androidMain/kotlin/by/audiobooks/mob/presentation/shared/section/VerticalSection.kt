package by.audiobooks.mob.presentation.shared.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.audiobooks.mob.presentation.model.SectionContent
import by.audiobooks.mob.presentation.navigation.Book
import by.audiobooks.mob.presentation.shared.SectionHeader
import by.audiobooks.mob.presentation.shared.book.VerticalBookPreview

@Composable
fun VerticalSection(
    content: SectionContent,
    navController: NavHostController,
    limit: Int = 0,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    SectionHeader(text = content.tag.name)
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
    ) {
        items(items = content.books.let {
            if (limit>0) {
                it.take(limit)
            } else {
                it
            }
        }) {
            VerticalBookPreview(
                item = it,
                onClick = {
                    navController.navigate(Book(bookUuid = it.uuid))
                }
            )
        }
    }
}