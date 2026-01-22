package com.bidone.data.repository.remote.products

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse
import com.bidone.data.model.remote.product.list.ProductsResponse
import com.bidone.data.util.Result

interface ProductsRepository {

    suspend fun getProducts(): Result<ProductsResponse>

    suspend fun getProductDetails(id: String): Result<ProductDetailsResponse>

}