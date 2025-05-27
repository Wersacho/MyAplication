package com.example.myaplication.common

// запечатанный класс
sealed class ResultState<out T> { // базовый класс, описывает возможные состояния результата

    data class Success<T>(val data: T) : ResultState<T>() // успешный результат, есть данные
    data class Error<T>(val message : String) : ResultState<T>() // ошибка, есть сообщение
    data object Loading : ResultState<Nothing>() // загрузка, данных пока нет
}