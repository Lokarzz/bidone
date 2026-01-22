package com.bidone.data.model.remote.product.list

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val title: String,
    val shortDescription: String,
    val image: String,
    val price: Float
)