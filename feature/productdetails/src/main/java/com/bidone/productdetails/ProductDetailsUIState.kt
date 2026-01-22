package com.bidone.productdetails

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI

internal data class ProductDetailsUIState(
    val productDetailsAPIState: APIState<ProductDetailsUI> = APIState.Idle
)
