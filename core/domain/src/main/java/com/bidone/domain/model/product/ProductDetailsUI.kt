package com.bidone.domain.model.product

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse

data class ProductDetailsUI(
    val id: String,
    val name: String,
    val longDescription: String,
    val bannerImage: String,
)

fun ProductDetailsResponse.toUI(): ProductDetailsUI {
    return ProductDetailsUI(
        id = id,
        name = title,
        longDescription = description,
        bannerImage = portraitImage,
    )
}