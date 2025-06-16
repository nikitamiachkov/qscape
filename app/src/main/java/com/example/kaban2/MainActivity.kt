package com.example.kaban2

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
//import androidx.core.app.ComponentActivity
import com.example.kaban2.Navigation.NavHost
import com.example.kaban2.Screens.NavigationScreen
import com.example.kaban2.ui.theme.TestForLectionTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Устанавливает тему приложения, определяя цвета, стили и т.д
            TestForLectionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                    NavHost()
                }
            }
        }
    }
}