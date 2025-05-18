package com.example.kaban2.Screens.MainScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kaban2.Screens.BuyScreen.BuyScreen
import com.example.kaban2.Screens.Components.BottomNavItem
import com.example.kaban2.Screens.DarkBottomNavigationBar
import com.example.kaban2.Screens.RateScreen.RateScreen


@Composable
fun MainScreen(navController: NavHostController) {
    val innerNavController = rememberNavController()

    Scaffold(
        containerColor = Color(0xFF121212), // Тёмный фон всего экрана
        bottomBar = {
            DarkBottomNavigationBar(innerNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNavController,
            startDestination = BottomNavItem.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Main.route) {
                Surface(color = Color(0xFF121212), modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Главная страница",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            composable(BottomNavItem.Buy.route) {
                BuyScreen(innerNavController)
            }
            composable(BottomNavItem.Rate.route) {
                RateScreen(innerNavController)
            }
        }
    }
}