package com.example.kaban2.Screens.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Screens.RateScreen.Message

@Composable
fun AdMessageBubble(message: Chat) {
    val backgroundColor = if (message.from) Color(0xFF0B81BC) else Color(0xFF2A2A2A)
    val alignment = if (message.from) Alignment.End else Alignment.Start
    val shape = if (message.from) {
        RoundedCornerShape(16.dp, 0.dp, 16.dp, 16.dp)
    } else {
        RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = if (message.from) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor, shape)
                .padding(12.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = message.message.toString(),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}