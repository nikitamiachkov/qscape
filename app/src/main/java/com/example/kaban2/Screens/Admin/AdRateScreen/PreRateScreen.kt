package com.example.kaban2.Screens.Admin.AdRateScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaban2.R

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kaban2.Domain.Constant.supabase
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Screens.Components.AdProjectCard
import com.example.kaban2.Screens.Components.ChatCard
import com.example.kaban2.Screens.Components.MessageBubble
import com.example.kaban2.Screens.Components.ServiceCard
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel
import com.example.kaban2.Screens.RateScreen.RateScreenViewModel
import io.github.jan.supabase.auth.auth


data class ChatPreview(
    val username: String,
    val lastMessage: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreRateScreen(navController: NavHostController) {

    val viewModel: PreRateScreenViewModel = viewModel()

    val books = viewModel.books
    val kolvo = viewModel.kolvo
    val kolvo2 = viewModel.kolvo2
    val prof = viewModel.books3

    val chatList = listOf(
        ChatPreview("Анна", "Здравствуйте! Хотела уточнить..."),
        ChatPreview("Павел", "Спасибо, всё работает отлично."),
        ChatPreview("Дмитрий", "Когда ожидать обновление?"),
        ChatPreview("Елена", "Я отправила документы."),
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0B81BC) // тёмный фон
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Активные чаты",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(kolvo) { index ->

                    val userId = supabase.auth.currentUserOrNull()?.id
                    var profile : Profile?
                    for (i in 0..kolvo2-1) {
                        if (prof.value?.get(i)?.user_id == books.value?.get(index)?.user_id && prof.value?.get(i)?.user_id != userId) {
                            profile = prof.value?.get(i)
                            ChatCard(profile,
                                books.value?.get(index),
                                navController) //books.value?[index].service_id
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }

                }
            }
        }
    }
}




