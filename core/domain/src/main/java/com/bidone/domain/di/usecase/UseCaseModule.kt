package com.bidone.domain.di.usecase

import com.bidone.domain.usecase.ProductsUseCase
import com.bidone.domain.usecase.ProductsUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun provideProductsUseCaseImp(productsUseCaseImp: ProductsUseCaseImp): ProductsUseCase


}