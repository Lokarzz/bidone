package com.bidone.domain.usecase.repository

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse
import com.bidone.data.model.remote.product.list.Product
import com.bidone.data.model.remote.product.list.ProductsResponse
import com.bidone.data.repository.remote.products.ProductsRepository
import com.bidone.data.util.Result

internal class SuccessFakeRepository : ProductsRepository {
    val fakeProducts = (1..20).map {
        Product(
            id = it.toString(),
            title = "Subtitle $it",
            shortDescription = "Short Description $it",
            image = "image",
            price = 99f,
        )
    }

    override suspend fun getProducts(): Result<ProductsResponse> {
        val products = fakeProducts
        return Result.Success(products)
    }

    override suspend fun getProductDetails(id: String): Result<ProductDetailsResponse> {
        return Result.Success(
            ProductDetailsResponse(
                id = id,
                title = "title 1",
                description = "description",
                portraitImage = "portraitImage",
                price = 12f,
                likeCount = 12,
                shortDescription = "",
                bannerImage = "",
                image = ""
            )
        )
    }
}

internal class FailFakeRepository : ProductsRepository {
    val errorMessage = "error_message"
    override suspend fun getProducts(): Result<ProductsResponse> {

        return Result.Failure(error = Throwable(errorMessage))
    }

    override suspend fun getProductDetails(id: String): Result<ProductDetailsResponse> {
        return Result.Failure(error = Throwable(errorMessage))
    }
}
