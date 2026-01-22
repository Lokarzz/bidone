package com.bidone.data.repository.remote.products.apiservice

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse
import com.bidone.data.model.remote.product.list.Product
import com.bidone.data.model.remote.product.list.ProductsResponse
import com.bidone.data.repository.remote.APIService


internal class SuccessFakeApiService : APIService {
    val fakeProducts = (1..20).map {
        Product(
            id = it.toString(),
            title = "",
            shortDescription = "",
            image = "",
            price = 0f,
        )
    }

    val fakeProductDetails = ProductDetailsResponse(
        id = "1",
        title = "title 1",
        description = "",
        portraitImage = "",
        price = 0f,
        likeCount = 0,
    )

    override suspend fun fetchProducts(): ProductsResponse {
        return fakeProducts
    }

    override suspend fun fetchProductDetails(id: String): ProductDetailsResponse {
        return fakeProductDetails
    }

}

internal class FailFakeApiService : APIService {
    val errorMessage = "error_message"

    override suspend fun fetchProducts(): ProductsResponse {
        throw Exception(errorMessage)
    }

    override suspend fun fetchProductDetails(id: String): ProductDetailsResponse {
        throw Exception(errorMessage)
    }

}