package com.example.currencyconverter.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.currencyconverter.Screen
import com.example.currencyconverter.ui.theme.LocalExtraColors

@Composable
fun BottomBar(navController: NavController){
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(Modifier.height(50.dp), containerColor = LocalExtraColors.current.bottomBar) {
        arrayOf(Screen.Rates, Screen.Convert).forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                icon = {
                    Icon(painterResource(it.icon),null)
                },
                label = {
                    Text(it.title)
                },
                onClick = {
                    navController.navigate(it.route){
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = Color.Transparent,
                    selectedIconColor = LocalExtraColors.current.onBottomBarSelected,
                    unselectedIconColor = LocalExtraColors.current.onBottomBarUnselected,
                    selectedTextColor = LocalExtraColors.current.onBottomBarSelected,
                    unselectedTextColor = LocalExtraColors.current.onBottomBarUnselected
                ),
                interactionSource = MutableInteractionSource(),
            )
        }
    }
}
