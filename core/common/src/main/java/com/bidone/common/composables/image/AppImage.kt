package com.bidone.common.composables.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage


@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    alpha: Float = DefaultAlpha,
    contentDescription: String
) {
    val model = if (LocalInspectionMode.current) {
        android.R.drawable.ic_menu_report_image
    } else {
        image
    }

    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter,
        alpha = alpha,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(android.R.drawable.ic_menu_report_image),
                contentDescription = "",
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun AppImagePreview() {
    AppImage(
        modifier = Modifier.size(36.dp),
        image = "",
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}

