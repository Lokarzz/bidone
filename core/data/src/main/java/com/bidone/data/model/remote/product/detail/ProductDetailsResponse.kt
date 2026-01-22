package com.bidone.data.model.remote.product.detail

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponse(
    val id: String,
    val title: String,
    val description: String,
    val shortDescription: String,
    val portraitImage: String,
    val bannerImage: String,
    val image : String,
    val price: Float,
    val likeCount: Int
)