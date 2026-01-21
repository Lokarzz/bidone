package com.bidone.common.composables.image

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    alpha: Float = DefaultAlpha,
    contentDescription: String
) {
    val image = if (LocalInspectionMode.current) android.R.drawable.ic_menu_report_image else image

    AsyncImage(
        modifier = modifier,
        model = image,
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = colorFilter,
        alpha = alpha,
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

