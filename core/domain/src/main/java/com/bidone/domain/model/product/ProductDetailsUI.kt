package com.bidone.domain.model.product

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse

data class ProductDetailsUI(
    val id: String,
    val name: String,
    val longDescription: String,
    val shortDescription: String,
    val bannerImage: String,
    val image: String,
)

fun ProductDetailsResponse.toUI(): ProductDetailsUI {
    return ProductDetailsUI(
        id = id,
        name = title,
        longDescription = description,
        shortDescription = shortDescription,
        bannerImage = portraitImage,
        image = image
    )
}