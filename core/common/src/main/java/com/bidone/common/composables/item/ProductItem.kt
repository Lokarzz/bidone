package com.bidone.common.composables.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bidone.common.R
import com.bidone.common.composables.image.AppImage


@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    body: String,
) {

    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AppImage(
                modifier = Modifier.size(124.dp),
                image = image,
                contentDescription = stringResource(R.string.product_item_image)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier) {
                Text(
                    modifier = Modifier, text = title, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier, text = body, style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ProductItem(
        modifier = Modifier, image = "",
        title = "title",
        body = "Sample Body",
    )
}