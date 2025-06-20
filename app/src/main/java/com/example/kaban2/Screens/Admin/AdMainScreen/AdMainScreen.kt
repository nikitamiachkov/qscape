package com.example.kaban2.Screens.Admin.AdMainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.R
import com.example.kaban2.Screens.Admin.AdBuyScreen.AdBuyScreen
import com.example.kaban2.Screens.Admin.AdRateScreen.AdRateScreen
import com.example.kaban2.Screens.Admin.AdRateScreen.PreRateScreen
import com.example.kaban2.Screens.BuyScreen.BuyScreen
import com.example.kaban2.Screens.Components.AdBottomNavItem
import com.example.kaban2.Screens.Components.AdProjectCard
import com.example.kaban2.Screens.Components.BottomNavItem
import com.example.kaban2.Screens.Components.ProjectCard
import com.example.kaban2.Screens.DarkBottomNavigationBar
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import com.example.kaban2.Screens.NavigationScreen
import com.example.kaban2.Screens.RateScreen.RateScreen

data class Project(
    val title: String,
    val progress: Float, // 0.0 to 1.0
    val status: String
)

@Composable
fun AdMainScreen(navController: NavHostController) {
    val innerNavController = rememberNavController()
    val viewModel: AdMainScreenViewModel = viewModel()

    val username = viewModel.username


    Scaffold(
        containerColor = Color(0xFF0B81BC),
        bottomBar = {
            DarkBottomNavigationBar(innerNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNavController,
            startDestination = AdBottomNavItem.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AdBottomNavItem.Main.route) {
                AdProjectsScreen(username)
            }
            composable(AdBottomNavItem.Buy.route) {
                AdBuyScreen(navController)

            }
            composable(AdBottomNavItem.Rate.route) {
                PreRateScreen(navController)
            }
        }
    }
}

@Composable
fun AdProjectsScreen(username : String?) {
    val projects = listOf(
        Project("Разработка сайта", 0.75f, "В процессе"),
        Project("Приложение для заказов", 0.4f, "На согласовании"),
        Project("CRM для клиента", 1.0f, "Завершён")
    )

    val viewModel: AdMainScreenViewModel = viewModel()
    val books = viewModel.books
    val serv = viewModel.books2
    val kolvo = viewModel.kolvo
    val kolvo2 = viewModel.kolvo2
    val prof = viewModel.books3

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item {
            // Аватар и имя
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Аватар",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = username.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Активные проекты компании",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(kolvo) { index ->

            var profile : Profile?
            for (i in 0..kolvo2-1) {
                if (prof.value?.get(i)?.user_id == books.value?.get(index)?.user_id) {
                    profile = prof.value?.get(i)
                    AdProjectCard(profile,
                        books.value?.get(index),
                        books.value?.get(index)?.let { serv.value?.get(it.service_id) }) //books.value?[index].service_id
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }



        }
    }
}


