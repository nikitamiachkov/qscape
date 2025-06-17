package com.example.kaban2.Screens.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Domain.models.Services
import com.example.kaban2.Screens.MainScreen.Project

@Composable
fun ProjectCard(project: Projects?, services : Services?) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            services?.let {
                Text(
                    text = it.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            project?.completeness?.toFloat()?.let {
                LinearProgressIndicator(
                    progress = it,
                    color = Color(0xFF0b81bc),
                    trackColor = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth().height(6.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Статус: ${project?.status}",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
    }
}