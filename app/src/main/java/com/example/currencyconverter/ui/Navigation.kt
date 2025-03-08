package com.example.currencyconverter.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.Screen
import com.example.currencyconverter.ui.screens.ConvertScreen
import com.example.currencyconverter.ui.screens.CurrenciesScreen

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.Rates.route, modifier = Modifier.padding(paddingValues)){
        composable(route = Screen.Rates.route) {
            viewModel.topBarTitle.value = Screen.Rates.title
            CurrenciesScreen(viewModel)
        }

        composable(route = Screen.Convert.route, ){
            viewModel.topBarTitle.value = Screen.Convert.title
            ConvertScreen(viewModel)
        }

    }
}