package by.audiobooks.mob.presentation.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopBar(
    text: String = "Top Bar text",
    onClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxWidth()
        .requiredHeight(40.dp)) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
        )
    }
}