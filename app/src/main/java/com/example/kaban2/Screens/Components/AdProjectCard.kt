package com.example.kaban2.Screens.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kaban2.Domain.models.Profile
import com.example.kaban2.Domain.models.Projects
import com.example.kaban2.Domain.models.Services
import com.example.kaban2.Screens.Admin.AdMainScreen.AdMainScreenViewModel
import com.example.kaban2.Screens.MainScreen.Project

@Composable
fun AdProjectCard(profile: Profile?, project: Projects?, services: Services?,
                  navController: NavController) {
    val viewModel: AdMainScreenViewModel = viewModel()

    // Состояние прогресса
    var progress by remember { mutableStateOf(project?.completeness?.toFloat() ?: 0f) }

    /*val currentProject = viewModel.projects.collectAsState().value
        .find { it.user_id == project?.user_id && it.service_id == project?.service_id }

    var progress = currentProject?.completeness?.toFloat() ?: 0f*/

    val projects by viewModel.projects.collectAsState()

    //val project = projects.find { it.user_id == project?.user_id && it.service_id == project?.service_id }

    var refreshKey by remember { mutableStateOf(0) }

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

            // Прогрессбар
            LinearProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                color = Color(0xFF0b81bc),
                trackColor = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопки управления прогрессом
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        progress = (progress - 0.1f).coerceAtLeast(0f)
                        viewModel.updateProjectProgress(project?.user_id, project?.service_id, progress, navController)
                        /*navController.popBackStack() // Удалить текущий экран
                        navController.navigate("main_screen")*/

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04547A))
                ) {
                    Text(text = "-10%")
                }


                Button(
                    onClick = {
                        progress = (progress + 0.1f).coerceAtMost(1f)
                        viewModel.updateProjectProgress(project?.user_id, project?.service_id, progress, navController) // если нужно
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04547A))
                ) {
                    Text(text = "+10%")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Статус: ${project?.status}",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Клиент: ${profile?.username} ${profile?.surname}",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
    }
}