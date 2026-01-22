package com.bidone.domain.usecase.productdetails

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import kotlinx.coroutines.flow.Flow

interface ProductDetailsUseCase {

    operator fun invoke(id: String): Flow<APIState<ProductDetailsUI>>
}