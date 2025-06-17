package com.example.kaban2.Screens.BuyScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kaban2.Domain.State.ResultState
import com.example.kaban2.Navigation.NavigationRoutes

import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import kotlinx.coroutines.runBlocking
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.LiveData
import com.example.kaban2.Domain.Constant.supabase
import com.example.kaban2.Screens.Components.ServiceCard

data class Service(
    val title: String,
    val description: String,
    val duration: String,
    val price: String
)

@Composable
fun BuyScreen(navController: NavHostController, mapScreenViewModel: BuyScreenViewModel = viewModel()) {

    val kolvo = mapScreenViewModel.kolvo

    val services = mapScreenViewModel.books.observeAsState(emptyList())

    val resultState by mapScreenViewModel.resultState.collectAsState()

    /*val services = listOf(
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
    )*/

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
                Log.e("ыыыыыы", services.value.toString())
                items(kolvo) { index ->
                    ServiceCard(service = services.value[index])
                }
            }
        }
    }
}


