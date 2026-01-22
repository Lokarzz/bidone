package com.bidone.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bidone.common.composables.item.ProductItem
import com.bidone.common.preview.DevicePreviews
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import com.bidone.domain.usecase.ProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable

@Serializable
object Products

fun NavGraphBuilder.productsScreen(
    modifier: Modifier = Modifier,
    onItemPress: (String) -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    composable<Products> {
        ProductsScreen(modifier = modifier, onItemPress = onItemPress, innerPadding = innerPadding)
    }
}


@Composable
internal fun ProductsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    productsViewModel: ProductsViewModel = hiltViewModel(),
    onItemPress: (String) -> Unit
) {

    val uiState by productsViewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = innerPadding,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val productsAPIState = uiState.productsAPIState) {
            is APIState.Error -> {}
            APIState.Idle -> {}
            APIState.Loading -> item {
                CircularProgressIndicator()
            }

            is APIState.Success -> productItems(productsAPIState.data, onItemPress = onItemPress)
        }
    }
}

private fun LazyListScope.productItems(products: List<ProductUI>, onItemPress: (String) -> Unit) {
    items(products) {
        ProductItem(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable(onClick = { onItemPress(it.id) }),
            image = it.image,
            title = it.name,
            body = it.shortDescription
        )
    }
}


@DevicePreviews
@Composable
private fun Preview() {
    ProductsScreen(
        modifier = Modifier.fillMaxSize(),
        innerPadding = PaddingValues(16.dp),
        onItemPress = {},
        productsViewModel = viewModel {
            ProductsViewModel(productsUseCase = object : ProductsUseCase {
                override fun invoke(): Flow<APIState<List<ProductUI>>> {
                    val productsPreview = (0..20).map {
                        ProductUI(
                            id = it.toString(),
                            name = "Product $it",
                            image = "",
                            shortDescription = "Short description $it",
                        )
                    }
                    return flow { emit(APIState.Success(productsPreview)) }
                }
            })
        })
}