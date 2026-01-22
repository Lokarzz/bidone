package com.bidone.data.repository.remote

import com.bidone.data.model.remote.product.detail.ProductDetailsResponse
import com.bidone.data.model.remote.product.list.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {

    @GET("Lokarzz/bidone/products")
    suspend fun fetchProducts(): ProductsResponse


    @GET("Lokarzz/bidone/products/{id}")
    suspend fun fetchProductDetails(@Path("id") id: String): ProductDetailsResponse

}