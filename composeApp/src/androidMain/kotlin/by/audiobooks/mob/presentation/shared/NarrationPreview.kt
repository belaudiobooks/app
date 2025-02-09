package by.audiobooks.mob.presentation.shared

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import by.audiobooks.mob.domain.BookCover
import coil3.compose.AsyncImage
import coil3.toBitmap

@Composable
fun NarrationPreview(
    item: BookCover,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .requiredWidth(210.dp)
        .height(272.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick)
    ) {
        val colorState: MutableState<Color> = remember { mutableStateOf<Color>(Color.White) }
        AsyncImage(
            model = item.coverImage,
            contentDescription = null,
            modifier = Modifier.requiredSize(210.dp)
                .clickable(onClick = onClick),
            onSuccess = { result ->
                val softwareBitmap = result.result.image.toBitmap().copy(Bitmap.Config.ARGB_8888, false)
                Palette.from(softwareBitmap).generate { palette ->
                    colorState.value = palette?.getLightMutedColor(android.graphics.Color.TRANSPARENT)?.let { Color(it) } ?: Color.White
                }
            }
        )
        Box(modifier = Modifier
            .background(color = colorState.value)
            .fillMaxSize()
        ) {
            Column {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable(onClick = onClick),
                    maxLines = 1,
                    lineHeight = 28.sp
                )
                Text(
                    text = item.authors.map { it.name.trim() }.joinToString(","),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable(onClick = onClick),
                    maxLines = 1,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }
        }

    }
}
