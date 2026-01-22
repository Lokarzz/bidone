package com.bidone.data.repository.remote.products

import com.bidone.data.repository.remote.products.apiservice.FailFakeApiService
import com.bidone.data.repository.remote.products.apiservice.SuccessFakeApiService
import com.bidone.data.util.Result
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductsRepositoryImpTest {

    @Test
    fun `fetch products successfully`() = runTest {
        val fakeApiService = SuccessFakeApiService()
        val productsRepository = ProductsRepositoryImp(fakeApiService)

        val result = productsRepository.getProducts()
        TestCase.assertTrue(result is Result.Success)
        assert((result as Result.Success).value == fakeApiService.fakeProducts)
    }

    @Test
    fun `fetch products unsuccessfully`() = runTest {
        val fakeApiService = FailFakeApiService()
        val productsRepository = ProductsRepositoryImp(fakeApiService)

        val result = productsRepository.getProducts()
        TestCase.assertTrue(result is Result.Failure)
        assert((result as Result.Failure).error.message == fakeApiService.errorMessage)
    }

    @Test
    fun `fetch product details successfully`() = runTest {
        val fakeApiService = SuccessFakeApiService()
        val productsRepository = ProductsRepositoryImp(fakeApiService)

        val result = productsRepository.getProductDetails("1")
        TestCase.assertTrue(result is Result.Success)
        assert((result as Result.Success).value == fakeApiService.fakeProductDetails)
    }

    @Test
    fun `fetch product details unsuccessfully`() = runTest {
        val fakeApiService = FailFakeApiService()
        val productsRepository = ProductsRepositoryImp(fakeApiService)

        val result = productsRepository.getProductDetails("1")
        TestCase.assertTrue(result is Result.Failure)
        assert((result as Result.Failure).error.message == fakeApiService.errorMessage)
    }

}