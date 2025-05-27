package com.example.myaplication.domain.repo

import android.net.Uri
import com.example.myaplication.common.ResultState
import com.example.myaplication.domain.models.BannerDataModels
import com.example.myaplication.domain.models.CartDataModels
import com.example.myaplication.domain.models.CategoryDataModels
import com.example.myaplication.domain.models.ProductDataModels
import com.example.myaplication.domain.models.UserData
import com.example.myaplication.domain.models.UserDataParent
import kotlinx.coroutines.flow.Flow

interface Repo {

    //поток данных (из Kotlin Coroutines)

    fun registerUserWithEmailAndPassword(userData: UserData) : Flow<ResultState<String>> //Регистрирует пользователя по email и паролю.
    fun loginUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> //Авторизует пользователя по email и паролю.
    fun getuserById(uid: String): Flow<ResultState<String>>//Получает пользователя по ID  из Firebase
    fun upDateUserData(userDataParent: UserDataParent): Flow<ResultState<String>> //Обновляет данные пользователя.
    fun userProfileImage(uri: Uri): Flow<ResultState<String>> //Загружает изображение профиля.
    fun getCategoriesInLimited(): Flow<ResultState<List<CategoryDataModels>>>//Получает ограниченное количество категорий
    fun getProductInLimited(): Flow<ResultState<List<ProductDataModels>>>//Получает ограниченное количество продуктов
    fun getAllProducts(): Flow<ResultState<List<ProductDataModels>>>//Получает все продукты
    fun getProductById(productId: String): Flow<ResultState<ProductDataModels>>//Получает продукт по его ID
    fun addToCart(cartDataModels: CartDataModels): Flow<ResultState<String>>//dобавляет товар в корзину
    fun addToFav(productDataModels: ProductDataModels): Flow<ResultState<String>>//Добавляет товар в избранное
    fun getallFav(): Flow<ResultState<List<ProductDataModels>>>//Получает все избранные товары
    fun getCart(): Flow<ResultState<List<ProductDataModels>>>//Получает товары в корзине
    fun getAllCategories(): Flow<ResultState<List<CategoryDataModels>>>//Получает все категории
    fun getCheckout(productId: String): Flow<ResultState<ProductDataModels>>//Получает данные продукта для оформления заказа
    fun getBanner(): Flow<ResultState<List<BannerDataModels>>>//Получает баннеры
    fun getSpecificCategoryItems(categoryName: String): Flow<ResultState<List<ProductDataModels>>>//Получает все товары из конкретной категории
    fun getAllSuggestedProducts(): Flow<ResultState<List<ProductDataModels>>>//Получает список рекомендованных продуктов
}