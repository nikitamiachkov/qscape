package com.example.kaban2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kaban2.Screens.Components.BottomNavItem
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DarkBottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Main,
        BottomNavItem.Buy,
        BottomNavItem.Rate
    )

    // Цвета бренда
    val primaryColor = Color(0xFF04547A)    // новый цвет панели
    val accentColor = Color(0xFF7884E3)    // второстепенный

    Column {
        // Белая полоса сверху
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.White)
        )

        NavigationBar(
            containerColor = primaryColor,
            contentColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                NavigationBarItem(
                    icon = { item.icon() },
                    label = { Text(item.label, color = Color.White) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.LightGray,
                        indicatorColor = accentColor
                    )
                )
            }
        }
    }
}

