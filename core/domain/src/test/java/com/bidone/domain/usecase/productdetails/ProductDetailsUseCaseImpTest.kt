@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bidone.domain.usecase.productdetails

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.usecase.repository.FailFakeRepository
import com.bidone.domain.usecase.repository.SuccessFakeRepository
import junit.framework.TestCase.assertTrue
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

class ProductDetailsUseCaseImpTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch product details successfully`() = runTest {
        val repository = SuccessFakeRepository()
        val useCase = ProductDetailsUseCaseImp(repository)

        assertTrue(useCase("1").first() is APIState.Loading)


        val apiState = useCase("1").drop(1).first()
        assertTrue(apiState is APIState.Success)
        assertTrue((apiState as APIState.Success).data.id == "1")

    }

    @Test
    fun `fetch product details unsuccessfully`() = runTest {
        val repository = FailFakeRepository()
        val useCase = ProductDetailsUseCaseImp(repository).invoke("1")


        assertTrue(useCase.first() is APIState.Loading)

        val apiState = useCase.drop(1).first()
        assertTrue(apiState is APIState.Error)
        assertTrue((apiState as APIState.Error).apiError.message == repository.errorMessage)
    }

}