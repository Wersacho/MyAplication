package com.example.myaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myaplication.presentation.LoginScreenUi
import com.example.myaplication.presentation.navigation.App
import com.example.myaplication.ui.theme.MyAplicationTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint // позволяет Hilt внедрять зависимости в этот класс
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            MyAplicationTheme {

                App(firebaseAuth)

            }
        }
    }
}