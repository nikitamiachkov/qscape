package com.example.kaban2.Screens.BuyScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kaban2.Screens.Components.ServiceCard

data class Service(
    val title: String,
    val description: String,
    val duration: String,
    val price: String
)

@Composable
fun BuyScreen(navController: NavHostController) {
    val services = listOf(
        Service(
            title = "Разработка сайтов",
            description = "Создание адаптивных и современных сайтов с индивидуальным дизайном.",
            duration = "От 2 недель",
            price = "От 50 000 ₽"
        ),
        Service(
            title = "Мобильные приложения",
            description = "Разработка приложений для Android и iOS с удобным интерфейсом.",
            duration = "От 1 месяца",
            price = "От 150 000 ₽"
        ),
        Service(
            title = "Интернет-магазины",
            description = "Создание мощных интернет-магазинов с интеграцией оплаты и доставки.",
            duration = "От 3 недель",
            price = "От 100 000 ₽"
        ),
        Service(
            title = "Продвижение в интернете",
            description = "SEO, SMM и контекстная реклама для роста вашего бизнеса.",
            duration = "По договорённости",
            price = "От 30 000 ₽/мес"
        )
    )

    Surface(
        color = Color(0xFF0B81BC),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Услуги компании",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(services.size) { index ->
                    ServiceCard(service = services[index])
                }
            }
        }
    }
}


