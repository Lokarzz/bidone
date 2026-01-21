@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bidone.products

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


internal class ProductsViewModelTest {

    val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch products successfully`() = runTest {
        val fakeProductsUseCase = SuccessFakeProductsUseCase()
        val productsViewModel = ProductsViewModel(productsUseCase = fakeProductsUseCase)

        assert(productsViewModel.uiState.value.productsAPIState is APIState.Idle)
        assert(productsViewModel.uiState.drop(1).first().productsAPIState is APIState.Loading)
        testScheduler.advanceUntilIdle()

        val productAPIState = productsViewModel.uiState.value.productsAPIState
        assert(productAPIState is APIState.Success)
        val successState = productAPIState as APIState.Success
        assert(successState.data == fakeProductsUseCase.fakeProducts)
    }

    @Test
    fun `fetch products unsuccessfully`() = runTest {
        val fakeProductsUseCase = FailFakeProductsUseCase()
        val productsViewModel = ProductsViewModel(productsUseCase = fakeProductsUseCase)

        assert(productsViewModel.uiState.value.productsAPIState is APIState.Idle)
        assert(productsViewModel.uiState.drop(1).first().productsAPIState is APIState.Loading)
        testScheduler.advanceUntilIdle()

        val productAPIState = productsViewModel.uiState.value.productsAPIState
        assert(productAPIState is APIState.Error)
        val successState = productAPIState as APIState.Error
        assert(successState.apiError == fakeProductsUseCase.fakeAPIError)
    }

}