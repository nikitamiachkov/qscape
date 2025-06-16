package com.example.kaban2.Screens.Components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

sealed class BottomNavItem(val route: String, val icon: @Composable () -> Unit, val label: String) {
    object Main : BottomNavItem("main", { Icon(Icons.Default.Home, "Main") }, "Профиль")
    object Buy : BottomNavItem("buy", { Icon(Icons.Default.ShoppingCart, "Buy") }, "Проекты")
    object Rate : BottomNavItem("rate", { Icon(Icons.Default.Home, "Rate") }, "Поддержка")
}