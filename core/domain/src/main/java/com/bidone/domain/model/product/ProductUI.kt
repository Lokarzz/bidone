package com.bidone.domain.model.product

import com.bidone.data.model.remote.product.list.Product


data class ProductUI(
    val id: String,
    val image: String,
    val name: String,
    val shortDescription: String,
    val price: Float
)


fun List<Product>.toUI(): List<ProductUI> {
    return map {
        ProductUI(
            id = it.id,
            image = it.image,
            name = it.title,
            shortDescription = it.shortDescription,
            price = it.price
        )
    }
}