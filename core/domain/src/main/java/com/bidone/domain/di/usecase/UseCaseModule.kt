package com.bidone.domain.di.usecase

import com.bidone.domain.usecase.productdetails.ProductDetailsUseCase
import com.bidone.domain.usecase.productdetails.ProductDetailsUseCaseImp
import com.bidone.domain.usecase.products.ProductsUseCase
import com.bidone.domain.usecase.products.ProductsUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun provideProductsUseCaseImp(productsUseCaseImp: ProductsUseCaseImp): ProductsUseCase

    @Binds
    abstract fun productDetailsUseCaseImp(productDetailsUseCaseImp: ProductDetailsUseCaseImp): ProductDetailsUseCase


}