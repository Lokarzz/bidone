package com.bidone.data.repository.remote.products

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse
import com.bidone.data.model.remote.product.list.ProductsResponse
import com.bidone.data.repository.remote.APIService
import com.bidone.data.util.Result
import com.bidone.data.util.safeSuspendRun
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(private val apiService: APIService) :
    ProductsRepository {

    override suspend fun getProducts(): Result<ProductsResponse> {
        return safeSuspendRun {
            apiService.fetchProducts()
        }
    }

    override suspend fun getProductDetails(id: String): Result<ProductDetailsResponse> {
        return safeSuspendRun {
            apiService.fetchProductDetails(id = id)
        }
    }
}