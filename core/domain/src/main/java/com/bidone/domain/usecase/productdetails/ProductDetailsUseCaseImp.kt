package com.bidone.domain.usecase.productdetails

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import com.bidone.domain.util.flow.applyDefaultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductDetailsUseCaseImp @Inject constructor() : ProductDetailsUseCase {


    override fun invoke(id: String): Flow<APIState<ProductDetailsUI>> {
        return flow {
            delay(2500)
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
        }.applyDefaultState()
    }

}