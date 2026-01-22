package com.bidone.domain.usecase.products

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import kotlinx.coroutines.flow.Flow

interface ProductsUseCase {

    operator fun invoke(): Flow<APIState<List<ProductUI>>>
}