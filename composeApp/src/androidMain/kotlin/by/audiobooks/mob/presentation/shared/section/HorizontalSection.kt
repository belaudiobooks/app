package by.audiobooks.mob.presentation.shared.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.audiobooks.mob.presentation.model.SectionContent
import by.audiobooks.mob.presentation.navigation.Book
import by.audiobooks.mob.presentation.navigation.Category
import by.audiobooks.mob.presentation.shared.book.HorizontalBookPreview
import by.audiobooks.mob.presentation.shared.SectionHeader

@Composable
fun HorizontalSection(
    content: SectionContent,
    navController: NavHostController,
    limit: Int = 0,
    modifier: Modifier = Modifier,
) {
    SectionHeader(
        text = content.tag.name + " (" + content.tag.bookCount + ")",
        onClick = {
            navController.navigate(Category(content.tag.id))
        }
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
    ) {
        items(items = content.books.let {
            if (limit>0) {
                it.take(limit)
            } else {
                it
            }
        }) {
            HorizontalBookPreview(
                item = it,
                onClick = {
                    navController.navigate(Book(bookUuid = it.uuid))
                }
            )
        }
    }
}