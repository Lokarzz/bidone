package com.bidone.data.model.remote.product.detail

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponse(
    val id: String,
    val title: String,
    val description: String,
    val portraitImage: String,
    val price: Float,
    val likeCount: Int
)