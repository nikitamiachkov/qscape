package com.example.kaban2.Screens.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kaban2.Domain.models.Chat
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Screens.Admin.AdRateScreen.ChatPreview

@Composable
fun ChatCard(profile : Profile?, chat: Chat?,
             navController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF04547A)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navController.navigate("chat_screen/${chat?.user_id}")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (profile != null) {
                Text(
                    text = profile.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat?.message.toString(),
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }
}