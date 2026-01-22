package com.bidone.productdetails.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LikesCount(
    modifier: Modifier = Modifier,
    likes: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = "Like",
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = likes.toString(), style = MaterialTheme.typography.bodyMedium, color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LikesCount(modifier = Modifier, likes = 99)
}