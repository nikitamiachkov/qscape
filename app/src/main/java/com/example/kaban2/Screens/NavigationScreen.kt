package com.example.kaban2.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kaban2.Screens.BuyScreen.BuyScreen
import com.example.kaban2.Screens.Components.BottomNavItem
import com.example.kaban2.Screens.MainScreen.MainScreen
import com.example.kaban2.Screens.RateScreen.RateScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    // Определяем текущий экран
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Только на этих экранах показываем нижнюю панель
    val showBottomBar = currentRoute in listOf(
        BottomNavItem.Main.route,
        BottomNavItem.Buy.route,
        BottomNavItem.Rate.route
    )

    Scaffold(
        containerColor = Color(0xFF121212),
        bottomBar = {
            if (showBottomBar) {
                DarkBottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Main.route) {
                MainScreen(navController)
            }
            composable(BottomNavItem.Buy.route) {
                BuyScreen(navController)
            }
            composable(BottomNavItem.Rate.route) {
                RateScreen(navController)
            }
        }
    }
}
