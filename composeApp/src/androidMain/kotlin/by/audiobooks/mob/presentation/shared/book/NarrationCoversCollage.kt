package by.audiobooks.mob.presentation.shared.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import by.audiobooks.mob.R
import by.audiobooks.mob.domain.BookDetails
import coil3.compose.AsyncImage

@Composable
fun NarrationCoversCollage(
    item: BookDetails,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .requiredSize(150.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(color = Color.Transparent,
                shape = RoundedCornerShape(16.dp)),
    ) {
        val coverSize: Int = when (item.narrations.size) {
            1 -> 150
            2 -> 135
            3 -> 120
            4 -> 105
            else -> (165 - item.narrations.size * 15).toInt()
        }
        val offset: Int = when (item.narrations.size) {
            1 -> 0
            else -> 15
        }
        item.narrations.sortedBy { it.coverImage }.forEachIndexed { idx, narration ->
            if (narration.coverImage.isBlank()) {
                Box(
                    modifier = Modifier.requiredSize(coverSize.dp)
                        .offset( x = (idx * offset).dp, y = (idx * offset).dp)
                        .clip(RoundedCornerShape(16.dp))
                ){
                    Image(
                        painter = painterResource(getRandomBackground()),
                        contentDescription = "Narration background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.requiredSize(coverSize.dp)
                    )
                    Column(
                        modifier = modifier.fillMaxSize()
                            .align(alignment = Alignment.Center)
                    ) {
                        Text(
                            text = item.authors.map { it.name.trim() }.joinToString(","),
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center,
                            overflow =  TextOverflow.Clip,
                            maxLines = 2,
                            minLines = 2
                        )
                        Text(
                            text = item.title.uppercase().trim(),
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            minLines = 2
                        )
                    }

                }
            } else {
                AsyncImage(
                    model = narration.coverImage,
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(coverSize.dp)
                        .offset( x = (idx * offset).dp, y = (idx * offset).dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onClick)
                )
            }

        }
    }
}

fun getRandomBackground() =
    listOf(
        R.mipmap.bg_narration_red,
        R.mipmap.bg_narration_blue,
        R.mipmap.bg_narration_grey,
        R.mipmap.bg_narration_green,
        R.mipmap.bg_narration_purple,
        R.mipmap.bg_narration_yellow,
    ).random()
