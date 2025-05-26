package com.example.myaplication.presentation.Utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(

    value: String, // Текущее значение текста
    onValueChange: (String)->Unit, // Функция, вызываемая при изменении текста
    label: String,
    modifier: Modifier = Modifier, // Позволяет настраивать внешний вид (размеры, отступы и т.д.)
    singleLine:Boolean = true, // Если true, поле будет однострочным
    leadingIcon: ImageVector? = null, // Иконка слева внутри поля
    visualTransformation: VisualTransformation = VisualTransformation.None, // 	Как текст отображается (например, скрытие пароля)
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default, // 	Настройки клавиатуры (тип, действия и т.д.)

    ){ OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon?.let {  { Icon(imageVector = it, contentDescription = null) } }
    )

}