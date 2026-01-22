package com.bidone.products

import com.bidone.domain.model.apistate.APIError
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import com.bidone.domain.usecase.products.ProductsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SuccessFakeProductsUseCase : ProductsUseCase {
    val fakeProducts = (0..20).map {
        ProductUI(
            id = it.toString(),
            name = "Product $it",
            image = "",
            shortDescription = "",
        )
    }

    override fun invoke(): Flow<APIState<List<ProductUI>>> {
        return flow {
            emit(APIState.Loading)
            delay(500)
            emit(APIState.Success(fakeProducts))
        }
    }

}

internal class FailFakeProductsUseCase : ProductsUseCase {
    val fakeAPIError = APIError(
        key = "key",
        message = "message",
        code = 1,
    )


    override fun invoke(): Flow<APIState<List<ProductUI>>> {
        return flow {
            emit(APIState.Loading)
            delay(500)
            emit(APIState.Error(fakeAPIError))
        }
    }

}