package by.audiobooks.mob.presentation.shared.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun VerticalBookPreview(
    item: BookDetails,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(0.dp)
        .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        NarrationCoversCollage(
            item = item,
            modifier = modifier.align(Alignment.Top),
            onClick = onClick)
        Column(
            modifier = modifier.padding(start = 14.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                modifier = Modifier.clickable(onClick = onClick),
                maxLines = 1,
                lineHeight = 28.sp
            )
            Text(
                text = item.authors.map { it.name.trim() }.joinToString(","),
                fontSize = 14.sp,
                modifier = Modifier.clickable(onClick = onClick),
                maxLines = 1,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }
    }
}
