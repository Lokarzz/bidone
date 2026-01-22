package com.bidone.productdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bidone.common.composables.image.AppImage
import com.bidone.common.preview.DevicePreviews
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import com.bidone.domain.usecase.productdetails.ProductDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    val id: String
)

fun NavController.toProductDetailsScreen(id: String) = navigate(ProductDetails(id = id))

fun NavGraphBuilder.productDetailsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    composable<ProductDetails> {
        ProductDetailsScreen(modifier = modifier, innerPadding = innerPadding)
    }
}

@Composable
internal fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    productsViewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val uiState by productsViewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (val productDetailsAPIState = uiState.productDetailsAPIState) {
            is APIState.Error -> {}
            APIState.Idle -> {}
            APIState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = innerPadding.calculateTopPadding()),
                        contentAlignment = Alignment.Center

                    ) {
                        CircularProgressIndicator(modifier = Modifier)
                    }
                }
            }

            is APIState.Success -> productDetails(productDetailsAPIState.data)
        }
        item {
            Spacer(modifier = Modifier.size(innerPadding.calculateBottomPadding()))
        }
    }
}

private fun LazyListScope.productDetails(productDetailsUI: ProductDetailsUI) {
    item {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        ) {
            AppImage(
                modifier = Modifier.fillMaxSize(),
                image = productDetailsUI.bannerImage,
                contentDescription = stringResource(R.string.product_banner_image),
                contentScale = ContentScale.Crop
            )
        }
    }
    item {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = productDetailsUI.name,
            style = MaterialTheme.typography.titleMedium
        )
    }
    item {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = productDetailsUI.longDescription,
            style = MaterialTheme.typography.bodySmall
        )
    }


}

@DevicePreviews
@Composable
private fun Preivew() {
    ProductDetailsScreen(
        modifier = Modifier.fillMaxSize(), productsViewModel = viewModel {
            ProductDetailsViewModel(productdetailsUseCase = object : ProductDetailsUseCase {
                override fun invoke(id: String): Flow<APIState<ProductDetailsUI>> {
                    return flow {
                        emit(
                            APIState.Success(
                                ProductDetailsUI(
                                    id = id,
                                    name = "Product Name",
                                    longDescription = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ",
                                    bannerImage = "",
                                )
                            )
                        )
                    }
                }

            }, savedStateHandle = SavedStateHandle())
        }, innerPadding = PaddingValues(0.dp)
    )
}