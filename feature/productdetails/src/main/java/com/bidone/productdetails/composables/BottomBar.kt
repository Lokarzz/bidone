package com.bidone.productdetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bidone.productdetails.R

@Composable
internal fun BottomBar(modifier: Modifier = Modifier, price: Float, onAddToCart: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$$price", style = MaterialTheme.typography.titleLarge)
        Button(onClick = onAddToCart) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = stringResource(R.string.shopping_cart)
            )
            Text(text = "Add to cart")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    BottomBar(price = 99f, onAddToCart = {})
}