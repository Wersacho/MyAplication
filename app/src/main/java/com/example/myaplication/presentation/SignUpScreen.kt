package com.example.myaplication.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myaplication.R
import com.example.myaplication.domain.models.UserData
import com.example.myaplication.presentation.Utils.CustomTextField
import com.example.myaplication.presentation.Utils.SuccessAlertDialog
import com.example.myaplication.presentation.navigation.Routes
import com.example.myaplication.presentation.navigation.SubNavigation
import com.example.myaplication.presentation.viewModels.ShoppingAppViewModel


@Composable // указывает, что функция создает UI с помощью Jetpack Compose
fun SignUpScreen(navController: NavController, viewModel: ShoppingAppViewModel = hiltViewModel()) { // функция, которая отрисовывает экран регистрации

    val state = viewModel.signUpScreenState.collectAsStateWithLifecycle()

    val context = LocalContext.current // нужен, если хочешь показать Toast или получить доступ к ресурсам


    if (state.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize()) {

            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }

    } else if (state.value.errorMessage != null) {

        Box(modifier = Modifier.fillMaxSize()) {

            Text(text = state.value.errorMessage!!)

        }
    } else if (state.value.userData != null) {

        SuccessAlertDialog (

            onClick = {
                navController.navigate(SubNavigation.MainHomeScreen)
            }

        )
    } else {

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

                            val userData = UserData(
                                firstName = firstName,
                                //lastName = lastName,
                                email = email,
                                password = password,
                                //phoneNumber = phoneNumber,
                            )

                            viewModel.createUser(userData)

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

                Text("Зарегистрироваться",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.white))

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Уже есть аккаунт?")
                TextButton(onClick = {
                    //перекидывает на экран входа
                    navController.navigate(Routes.LoginScreen)
                }) {
                    Text("Войти", color = colorResource(id = R.color.red))
                }
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

            // вход с гугл
            OutlinedButton(onClick = { },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                shape = RoundedCornerShape(64.dp)
            ){

                //иконка
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp))

                //gutter=8dp
                Spacer(modifier = Modifier.size(8.dp))

                //подпись
                Text("Войти с Google")
            }

        }


    }



}