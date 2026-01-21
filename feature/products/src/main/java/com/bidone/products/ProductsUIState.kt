package com.bidone.products

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI

data class ProductsUIState(
    val productsAPIState: APIState<List<ProductUI>> = APIState.Idle
)