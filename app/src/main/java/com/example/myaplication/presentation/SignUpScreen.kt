package com.example.myaplication.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myaplication.R
import com.example.myaplication.presentation.Utils.CustomTextField

@Preview(showSystemUi = true) // 	Позволяет просматривать UI без запуска
@Composable // указывает, что функция создает UI с помощью Jetpack Compose
fun SignUpScreen(){ // функция, которая отрисовывает экран регистрации

    val context = LocalContext.current // нужен, если хочешь показать Toast или получить доступ к ресурсам
    var firstName by remember { mutableStateOf("") } // создаёт переменную с состоянием, которую Jetpack Compose будет отслеживать.
    //var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    //var phoneNumber by remember { mutableStateOf("") } // поля ввода
    var email by remember { mutableStateOf("") }

    Column ( modifier = Modifier.fillMaxSize().padding(16.dp), // это вертикальный контейнер, fillMaxSize() — заполняет весь экран
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

        // Поле ввода: фамилия
        //CustomTextField(

        //    value = lastName,
        //    onValueChange = {lastName = it},
        //    label = "Фамилия",
        //    leadingIcon = Icons.Default.Person,
        //    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

        //    )

        // Поле ввода: почта
        CustomTextField(

            value = email,
            onValueChange = {email = it},
            label = "Почта",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

            )


        // Поле ввода: телефон
        //CustomTextField(
        //
        //    value = phoneNumber,
        //    onValueChange = {phoneNumber = it},
        // label = "Телефон",
        //   leadingIcon = Icons.Default.Phone,
        //    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        //
        //    )

        // Поле ввода: пароль
        CustomTextField(

            value = password,
            onValueChange = {password = it},
            label = "Пароль",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            visualTransformation = PasswordVisualTransformation(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

        // Поле ввода: подтверждение пароля
        CustomTextField(

            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = "Повторите пароль",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            visualTransformation = PasswordVisualTransformation(),
            )

        Button(

            onClick = {
                // проверяет заполнены ли поля регистрации
                //firstName.isNotEmpty()
                //phoneNumber.isNotEmpty()
                if(firstName.isNotEmpty() && email.isNotEmpty()  && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                    if (password == confirmPassword){ // проверяет, что пароли совпадают
                        Toast.makeText(context, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show() // Toast.LENGTH_SHORT 2 сек
                    }
                    else {
                        Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context, "Не все поля регистрации заполнены", Toast.LENGTH_SHORT).show()
                }
            },

            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red))

        ){

            Text("Зарегистрироваться", color = colorResource(id = R.color.white))

        }

        // разделительная строка с надписью "ИЛИ" посередине
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),

            verticalAlignment = Alignment.CenterVertically // выравнивание по вертикали: все элементы в строке выровнены по центру

            ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.weight(1f)
            ) // линия, занимает все доступное пространство - 1f

            Text(text = "ИЛИ", modifier = Modifier.padding(horizontal = 8.dp)) // "ИЛИ" по бокам отступы 8.dp

            HorizontalDivider(modifier = Modifier.weight(1f)) // линия, занимает все доступное пространство - 1f
        }

        OutlinedButton(onClick = { },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp)
            ){

            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                modifier = Modifier.size(24.dp))


        }

    }
}