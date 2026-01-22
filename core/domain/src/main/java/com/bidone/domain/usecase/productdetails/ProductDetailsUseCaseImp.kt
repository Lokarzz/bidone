package com.bidone.domain.usecase.productdetails

import com.bidone.data.repository.remote.products.ProductsRepository
import com.bidone.data.util.Result.Failure
import com.bidone.data.util.Result.Success
import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.model.product.ProductDetailsUI
import com.bidone.domain.model.product.toUI
import com.bidone.domain.util.flow.applyDefaultState
import com.bidone.domain.util.throwable.handleError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProductDetailsUseCaseImp @Inject constructor(val productsRepository: ProductsRepository) :
    ProductDetailsUseCase {


    override fun invoke(id: String): Flow<APIState<ProductDetailsUI>> {
        return flow {
            when (val result = productsRepository.getProductDetails(id = id)) {
                is Failure -> emit(APIState.Error(result.error.handleError()))
                is Success -> emit(APIState.Success(result.value.toUI()))
            }
        }.applyDefaultState()
    }

}