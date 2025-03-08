package com.example.currencyconverter.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Bottom Bar Colors
val LightBottomBar = Color(0xFFF5F5F5)  // Light Gray
val DarkBottomBar = Color(0xFF1E1E1E)   // Dark Gray

// Icons on Bottom Bar
val LightOnBottomBarSelected = Color(0xFF6200EE) // Medium Gray
val DarkOnBottomBarSelected = Color(0xFFBB86FC)  // Lighter Gray

// Icons on Bottom Bar
val LightOnBottomBarUnselected = Color(0xFF757575) // Medium Gray
val DarkOnBottomBarUnselected = Color(0xFFB0B0B0)  // Lighter Gray

// Top Bar Colors
val LightTopBar = Color(0xFFFFFFFF) // White
val DarkTopBar = Color(0xFF121212)  // Almost Black

// Card Colors
val LightCard = Color(0xFFF8F8F8) // Slightly Off-White
val DarkCard = Color(0xFF1C1C1C)  // Darker Gray

// Dialog Colors
val LightDialogSelected = Color(0xFFDDDDDD)
val DarkDialogSelected = Color(0xFF333333)

@Immutable
data class ExtraColors(
    val bottomBar: Color,
    val topBar: Color,
    val onBottomBarSelected: Color,
    val onBottomBarUnselected: Color,
    val card: Color,
    val dialogSelected: Color

)
val LightExtraColors = ExtraColors(
    bottomBar = LightBottomBar,
    topBar = LightTopBar,
    onBottomBarSelected = LightOnBottomBarSelected,
    onBottomBarUnselected = LightOnBottomBarUnselected,
    card = LightCard,
    dialogSelected = LightDialogSelected
)

val DarkExtraColors = ExtraColors(
    bottomBar = DarkBottomBar,
    topBar = DarkTopBar,
    onBottomBarSelected = DarkOnBottomBarSelected,
    onBottomBarUnselected = DarkOnBottomBarUnselected,
    card = DarkCard,
    dialogSelected = DarkDialogSelected
)

val LocalExtraColors = staticCompositionLocalOf { LightExtraColors }
