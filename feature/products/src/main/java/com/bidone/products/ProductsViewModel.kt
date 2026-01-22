package com.bidone.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bidone.domain.usecase.products.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productsUseCase().collect {
                _uiState.value = _uiState.value.copy(productsAPIState = it)
            }
        }
    }
}