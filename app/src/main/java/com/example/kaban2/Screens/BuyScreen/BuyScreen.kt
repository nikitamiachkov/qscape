package com.example.kaban2.Screens.BuyScreen

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
import com.example.kaban2.Screens.Components.BottomNavItem
import com.example.kaban2.Screens.DarkBottomNavigationBar
import com.example.kaban2.Screens.RateScreen.RateScreen


@Composable
fun BuyScreen(navController: NavHostController) {
    Surface(color = Color(0xFF121212), modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Купить крипту",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
}