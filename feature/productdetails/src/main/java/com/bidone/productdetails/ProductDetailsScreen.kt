package com.bidone.productdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.bidone.common.composables.item.ProductItem
import com.bidone.common.composables.toolbar.DetailsAppToolBar
import com.bidone.common.preview.DevicePreviews
import com.bidone.common.util.context.showToast
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import com.bidone.domain.usecase.productdetails.ProductDetailsUseCase
import com.bidone.productdetails.composables.BottomBar
import com.bidone.productdetails.composables.LikesCount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
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
    onBackPress: () -> Unit,
) {
    composable<ProductDetails> {
        ProductDetailsScreen(
            modifier = modifier, innerPadding = innerPadding,
            onBackPress = onBackPress,
        )
    }
}

@Composable
internal fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    productsViewModel: ProductDetailsViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    val uiState by productsViewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailsLaunchEffect(effect = productsViewModel.effect)
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (val productDetailsAPIState = uiState.productDetailsAPIState) {
                is APIState.Error -> {}
                APIState.Idle -> {}
                APIState.Loading -> loadingItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = innerPadding.calculateTopPadding())
                )

                is APIState.Success -> productDetails(
                    productDetailsAPIState.data, innerPadding = innerPadding
                )
            }
            item {
                Spacer(modifier = Modifier.size(innerPadding.calculateBottomPadding() + 64.dp))
            }
        }
        Column(modifier = Modifier) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(innerPadding.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.background.copy(alpha = .25f))
            )
            DetailsAppToolBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onBackPress = onBackPress,
                onShare = {})
        }
        if (uiState.productDetailsAPIState is APIState.Success) {
            BottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                price = (uiState.productDetailsAPIState as APIState.Success<ProductDetailsUI>).data.price,
                onAddToCart = {})
        }
    }
}

private fun LazyListScope.loadingItem(modifier: Modifier = Modifier) {
    item {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center

        ) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }
}

@Composable
private fun ProductDetailsLaunchEffect(effect: SharedFlow<ProductDetailsEffect>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        effect.collect {
            when (it) {
                is ProductDetailsEffect.OnError -> {
                    context.showToast(it.message)
                }
            }
        }
    }
}


private fun LazyListScope.productDetails(
    productDetailsUI: ProductDetailsUI, innerPadding: PaddingValues
) {
    item {
        Box(modifier = Modifier) {
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

    }

    item {
        ProductItem(
            modifier = Modifier, image = productDetailsUI.image,
            title = productDetailsUI.name,
            body = productDetailsUI.shortDescription,
            price = productDetailsUI.price,
        )
    }

    item {
        LikesCount(modifier = Modifier.padding(horizontal = 16.dp), likes = productDetailsUI.likes)
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
        modifier = Modifier.fillMaxSize(),
        productsViewModel = viewModel {
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
                                    shortDescription = "asdf",
                                    image = "asdf",
                                    price = 99f,
                                    likes = 5
                                )
                            )
                        )
                    }
                }

            }, savedStateHandle = SavedStateHandle())
        },
        onBackPress = {},
    )
}