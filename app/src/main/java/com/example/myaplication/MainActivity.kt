package com.example.myaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myaplication.presentation.LoginScreenUi
import com.example.myaplication.ui.theme.MyAplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // позволяет Hilt внедрять зависимости в этот класс
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAplicationTheme {

                LoginScreenUi(navController)

                }
            }
        }
    }