@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bidone.productdetails

import androidx.lifecycle.SavedStateHandle
import com.bidone.domain.model.apistate.APIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductDetailsViewModelTest {

    val testDispatcher = StandardTestDispatcher()

    val savedStateHandle = SavedStateHandle()

    @Before
    fun setUp() {
        savedStateHandle["id"] = "1"

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `fetch product details successfuly`() = runTest {

        val productDetailsViewModel = ProductDetailsViewModel(
            productdetailsUseCase = SuccessFakeProductDetailsUseCase(),
            savedStateHandle = savedStateHandle
        )
        assert(productDetailsViewModel.uiState.value.productDetailsAPIState is APIState.Idle)
        assert(
            productDetailsViewModel.uiState.drop(1)
                .first().productDetailsAPIState is APIState.Loading
        )
        testScheduler.advanceUntilIdle()

        val productAPIState = productDetailsViewModel.uiState.value.productDetailsAPIState
        assert(productAPIState is APIState.Success)
        val successState = productAPIState as APIState.Success
        assert(successState.data.id == savedStateHandle.get<String>("id"))
    }

    @Test
    fun `fetch product details unsuccessfully`() = runTest {
        val fakeProductsUseCase = FailFakeProductDetailsUseCase()
        val productsViewModel = ProductDetailsViewModel(
            productdetailsUseCase = fakeProductsUseCase,
            savedStateHandle = savedStateHandle
        )

        assert(productsViewModel.uiState.value.productDetailsAPIState is APIState.Idle)
        assert(productsViewModel.uiState.drop(1).first().productDetailsAPIState is APIState.Loading)
        testScheduler.advanceUntilIdle()

        val productAPIState = productsViewModel.uiState.value.productDetailsAPIState
        assert(productAPIState is APIState.Error)
        val successState = productAPIState as APIState.Error
        assert(successState.apiError == fakeProductsUseCase.fakeAPIError)
    }
}