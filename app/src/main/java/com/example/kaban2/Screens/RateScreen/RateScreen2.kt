package com.example.kaban2.Screens.RateScreen

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Screens.Components.MessageBubble
import com.example.kaban2.Screens.Components.ServiceCard
import com.example.kaban2.Screens.MainScreen.MainScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateScreen2(navController: NavHostController) {
    var messages1 by remember { mutableStateOf(
        listOf(
            Chat("", false, "Здравствуйте! Чем можем помочь?"),
            //Message("Привет! У меня вопрос по заказу.", true)
        )
    ) }

    var messages by remember { mutableStateOf(
        listOf(
            Chat("", false, null),
            //Message("Привет! У меня вопрос по заказу.", true)
        )
    ) }

    val viewModel: RateScreenViewModel = viewModel()

    val chat = viewModel.books.observeAsState(emptyList())
    val kolvo = viewModel.kolvo

    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    Surface(
        color = Color(0xFF121212),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Верхняя панель с аватаром и именем поддержки
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.icon), // аватар поддержки
                            contentDescription = "Аватар поддержки",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Поддержка",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors( // или просто использовать medium, если small нет
                    containerColor = Color(0xFF0B81BC),
                    titleContentColor = Color.White
                )
            )

            // Основная часть — переписка
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                reverseLayout = false
            ) {
                items(1) { message ->
                    MessageBubble(messages1[0])
                    Spacer(modifier = Modifier.height(8.dp))
                }
                //MessageBubble(messages[0])
                items(kolvo) { index ->
                    MessageBubble(chat.value[index])
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(messages) { message ->
                    if (message.message != null) MessageBubble(message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Нижняя панель ввода сообщения
            Row(
                modifier = Modifier
                    .background(Color(0xFF1E1E1E))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                        .background(Color(0xFF2A2A2A), RoundedCornerShape(24.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 16.sp),
                    cursorBrush = SolidColor(Color.White)
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        val text = inputText.text.trim()
                        if (text.isNotEmpty()) {
                            viewModel.insertUserData(text)
                            messages = messages + Chat("", true, text)
                            inputText = TextFieldValue("")
                            // Здесь можно добавить отправку сообщения на сервер, если нужно
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon), // иконка отправки (подключи свою)
                        contentDescription = "Отправить",
                        tint = Color(0xFF0B81BC)
                    )
                }
            }
        }
    }
}


