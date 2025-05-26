package com.example.myaplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myaplication.presentation.Utils.CustomTextField

@Preview(showSystemUi = true) // 	Позволяет просматривать UI без запуска
@Composable // указывает, что функция создает UI с помощью Jetpack Compose
fun SignUpScreen(){ // функция, которая отрисовывает экран регистрации

    val context = LocalContext.current // нужен, если хочешь, например, показать Toast или получить доступ к ресурсам
    var firstName by remember { mutableStateOf("") } // создаёт переменную с состоянием, которую Jetpack Compose будет отслеживать.
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") } // поля ввода

    Column ( modifier = Modifier.fillMaxSize(), // это вертикальный контейнер, fillMaxSize() — заполняет весь экран
        horizontalAlignment = Alignment.CenterHorizontally, // horizontalAlignment — выравнивает содержимое по горизонтали (по центру).


        ) {
        //  Заголовок
        Text(
            text = "Регистрация",
            fontSize = 24.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp).align(Alignment.Start) // Отступ сверху и снизу — 16.dp
            // Выравнивание по левому краю: .align(Alignment.Start).

        )

        // Поле ввода: Имя
        CustomTextField(

            value = firstName,
            onValueChange = {firstName = it},
            label = "Имя",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

        )

    }
}