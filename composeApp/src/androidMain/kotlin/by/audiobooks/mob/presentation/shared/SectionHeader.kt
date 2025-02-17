package by.audiobooks.mob.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionHeader(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth()
                .padding(14.dp, 20.dp, 14.dp, 10.dp),
            fontSize = 18.sp,
        )
    }
}

@Composable
fun ErrorHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(color = Color.Red)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth()
                .padding(14.dp, 5.dp, 14.dp, 10.dp),
            fontSize = 16.sp
        )
    }
}