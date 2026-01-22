package com.bidone.bidtest.ui.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bidone.productdetails.productDetailsScreen
import com.bidone.productdetails.toProductDetailsScreen
import com.bidone.products.Products
import com.bidone.products.productsScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            navController = navController,
            startDestination = Products
        ) {
            productsScreen(
                modifier = Modifier.fillMaxSize(),
                onItemPress = navController::toProductDetailsScreen,
                innerPadding = innerPadding
            )
            productDetailsScreen(modifier = Modifier, innerPadding = innerPadding)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}

