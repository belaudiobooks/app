package by.audiobooks.mob.presentation.shared.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.audiobooks.mob.domain.BookDetails

@Composable
fun HorizontalBookPreview(
    item: BookDetails,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .requiredWidth(150.dp)
        .height(193.dp)
        .padding(0.dp)
        .clickable(onClick = onClick),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        NarrationCoversCollage(
            item = item,
            modifier = modifier.align(Alignment.Start),
            onClick = onClick)
        Text(
            text = item.title,
            fontSize = 14.sp,
            modifier = Modifier.clickable(onClick = onClick),
            maxLines = 1,
            lineHeight = 22.sp
        )
        Text(
            text = item.authors.map { it.name.trim() }.joinToString(","),
            fontSize = 12.sp,
            modifier = Modifier.clickable(onClick = onClick),
            maxLines = 1,
            color = Color.Gray,
            lineHeight = 16.sp
        )
    }
}
