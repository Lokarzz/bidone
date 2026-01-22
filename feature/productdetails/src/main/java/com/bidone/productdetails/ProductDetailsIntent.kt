package com.bidone.productdetails


internal interface ProductDetailsEffect {
    data class OnError(val message: String) : ProductDetailsEffect

}