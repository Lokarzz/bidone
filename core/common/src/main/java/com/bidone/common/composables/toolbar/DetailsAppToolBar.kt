package com.bidone.common.composables.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bidone.common.R

@Composable
fun DetailsAppToolBar(modifier: Modifier = Modifier, onBackPress: () -> Unit, onShare: () -> Unit) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        FilledIconButton(onClick = onBackPress) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = stringResource(R.string.on_back_press)
            )
        }
        FilledIconButton(onClick = onShare) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = stringResource(R.string.on_back_press)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailsAppToolBar(
        modifier = Modifier.fillMaxWidth(),
        onBackPress = {},
        onShare = {}
    )
}