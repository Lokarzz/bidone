package com.bidone.domain.model.apistate

data class APIError(
    val key: String,
    val message: String,
    val code: Int
)