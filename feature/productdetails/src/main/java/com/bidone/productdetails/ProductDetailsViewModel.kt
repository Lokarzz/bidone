package com.bidone.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bidone.domain.usecase.productdetails.ProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailsViewModel @Inject constructor(
    private val productdetailsUseCase: ProductDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _uiState = MutableStateFlow(ProductDetailsUIState())
    val uiState = _uiState.asStateFlow()

    val id = savedStateHandle.get<String>("id") ?: ""

    init {
        fetchProductDetails(id)
    }

    private fun fetchProductDetails(id: String) {
        viewModelScope.launch {
            productdetailsUseCase(id = id).collect {
                _uiState.value = _uiState.value.copy(productDetailsAPIState = it)
            }
        }
    }

}