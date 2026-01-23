package com.bidone.productdetails

import com.bidone.domain.model.apistate.APIError
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import com.bidone.domain.usecase.productdetails.ProductDetailsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SuccessFakeProductDetailsUseCase : ProductDetailsUseCase {


    override fun invoke(id: String): Flow<APIState<ProductDetailsUI>> {
        return flow {
            emit(APIState.Loading)
            delay(500)
            emit(
                APIState.Success(
                    ProductDetailsUI(
                        id = id,
                        name = "Product Name",
                        longDescription = "",
                        bannerImage = "",
                        shortDescription = "",
                        image = "",
                        price = 0f,
                        likes = 0
                    )
                )
            )
        }
    }

}

internal class FailFakeProductDetailsUseCase : ProductDetailsUseCase {
    val fakeAPIError = APIError(
        key = "key",
        message = "message",
        code = 1,
    )

    override fun invoke(id: String): Flow<APIState<ProductDetailsUI>> {
        return flow {
            emit(APIState.Loading)
            delay(500)
            emit(APIState.Error(fakeAPIError))
        }
    }

}