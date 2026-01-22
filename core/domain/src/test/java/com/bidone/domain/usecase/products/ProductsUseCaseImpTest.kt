package com.bidone.domain.usecase.products

import com.bidone.domain.model.apistate.APIState
import com.bidone.domain.usecase.ProductsUseCaseImp
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductsUseCaseImpTest {


    @Test
    fun `fetch products successfully`() = runTest {
        val useCase = ProductsUseCaseImp()


        assertTrue(useCase().first() is APIState.Loading)

        val apiState = useCase().drop(1).first()
        assertTrue(apiState is APIState.Success)
        assertTrue((apiState as APIState.Success).data.isNotEmpty())
        assertTrue(apiState.data == useCase.previewProducts)
    }


}