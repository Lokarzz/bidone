package com.bidone.domain.usecase

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import com.bidone.domain.util.flow.applyDefaultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductsUseCaseImp @Inject constructor() : ProductsUseCase {

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
            delay(2500)
            emit(APIState.Success(previewProducts))
        }.applyDefaultState()
    }

}