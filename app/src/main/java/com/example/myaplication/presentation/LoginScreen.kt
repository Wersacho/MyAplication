package com.example.myaplication.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.myaplication.R
import com.example.myaplication.domain.models.UserData
import com.example.myaplication.presentation.Utils.CustomTextField
import com.example.myaplication.presentation.Utils.SuccessAlertDialog
import com.example.myaplication.presentation.navigation.Routes
import com.example.myaplication.presentation.navigation.SubNavigation
import com.example.myaplication.presentation.viewModels.ShoppingAppViewModel

@Composable
fun LoginScreenUi(navController: NavHostController, viewModel: ShoppingAppViewModel = hiltViewModel()) {

    val state = viewModel.loginScreenState.collectAsStateWithLifecycle()
    val showDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current


    if (state.value.isLoading){

        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    } else if (state.value.errorMessage != null) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = state.value.errorMessage!!)
        }


    } else if(state.value.userData != null){

        SuccessAlertDialog(
            onClick = {
                navController.navigate(SubNavigation.MainHomeScreen)
            }

        )
    } else {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){

            Text(
                text = "Войти",
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 16.dp).align(Alignment.Start)

            )

            CustomTextField(

                value = email,
                onValueChange = {email = it},
                label = "Почта",
                leadingIcon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

                )

            Spacer(modifier = Modifier.padding(8.dp))

            CustomTextField(

                value = password,
                onValueChange = {password = it},
                label = "Пароль",
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = Icons.Default.Lock,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

                )

            Spacer(modifier = Modifier.padding(4.dp))


            Text(text = "Забыли пароль?",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                onClick = {

                    if (email.isNotBlank() && password.isNotBlank()){

                        val userData = UserData(
                            firstName = "",
                            //lastName = "",
                            email = email,
                            password = password,
                            //phoneNumber = ""
                        )

                        viewModel.loginUser(userData)

                        Toast.makeText(context, "Вход успешно выполнен", Toast.LENGTH_SHORT).show()

                    } else {

                        Toast.makeText(context, "Не все поля заполнены!", Toast.LENGTH_SHORT).show()

                    }

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(64.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red)),
                border = BorderStroke(1.dp, colorResource(id = R.color.red))

            ) {

                Text(text = "Войти", color = colorResource(R.color.white))

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Еще нет аккаунта?")
                TextButton(onClick = {
                    //перекидывает на экран входа
                    navController.navigate(Routes.SignUpScreen)
                }) {
                    Text("Восстановить", color = colorResource(id = R.color.red))
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