package com.bidone.domain.usecase

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ProductsUseCaseImp : ProductsUseCase {

    val previewProducts = (0..20).map {
        ProductUI(
            id = it.toString(),
            name = "Product $it",
            shortDescription = "Short Description $it",
            image = ""
        )
    }

    override fun invoke(): Flow<APIState<List<ProductUI>>> {
        return flow {
            emit(APIState.Success(previewProducts))
        }
    }

}