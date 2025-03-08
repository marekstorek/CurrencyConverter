package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.ui.Navigation
import com.example.currencyconverter.ui.components.BottomBar
import com.example.currencyconverter.ui.components.TopBar
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import com.example.currencyconverter.ui.theme.LocalExtraColors

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataState by viewModel.dataState
        val topBarTitle by viewModel.topBarTitle

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(
            condition = {
                dataState.loading
            }
        )

        setContent {
            CurrencyConverterTheme {

                SetSystemBarColours()

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .statusBarsPadding(),
                    topBar = {
                        TopBar(topBarTitle)
                    },
                    bottomBar = {
                        BottomBar(navController)
                    }
                ) { paddingValues ->
                        Navigation(navController, viewModel, paddingValues)
                }
            }
        }
    }

    @Composable
    private fun SetSystemBarColours() {
        val context = LocalContext.current
        val window = (context as ComponentActivity).window
        window.statusBarColor = LocalExtraColors.current.topBar.toArgb()
        window.navigationBarColor = LocalExtraColors.current.bottomBar.toArgb()
        val useDarkIcons = MaterialTheme.colorScheme.background.luminance() > 0.5f

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = useDarkIcons
            isAppearanceLightNavigationBars = useDarkIcons
        }
    }
}

