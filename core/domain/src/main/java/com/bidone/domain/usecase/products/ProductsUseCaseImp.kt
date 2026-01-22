package com.bidone.domain.usecase.products

import com.bidone.data.repository.remote.products.ProductsRepository
import com.bidone.data.util.Result
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductUI
import com.bidone.domain.model.product.toUI
import com.bidone.domain.util.flow.applyDefaultState
import com.bidone.domain.util.throwable.handleError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductsUseCaseImp @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductsUseCase {

    override fun invoke(): Flow<APIState<List<ProductUI>>> {
        return flow {
            when (val result = productsRepository.getProducts()) {
                is Result.Failure -> emit(APIState.Error(result.error.handleError()))
                is Result.Success -> emit(APIState.Success(result.value.toUI()))
            }
        }.applyDefaultState()
    }


}