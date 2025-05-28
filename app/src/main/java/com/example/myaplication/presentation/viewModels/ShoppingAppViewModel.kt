package com.example.myaplication.presentation.viewModels

import com.example.myaplication.domain.models.CategoryDataModels
import com.example.myaplication.domain.models.ProductDataModels
import com.example.myaplication.domain.models.UserDataParent
import com.example.myaplication.domain.useCase.AddToFavUseCase
import com.example.myaplication.domain.useCase.AddtoCardUseCase
import com.example.myaplication.domain.useCase.CreateUserUseCase
import com.example.myaplication.domain.useCase.GetAllCategoryUseCase
import com.example.myaplication.domain.useCase.GetAllFavUseCase
import com.example.myaplication.domain.useCase.GetAllProductUseCase
import com.example.myaplication.domain.useCase.GetAllSuggestedProductsUseCase
import com.example.myaplication.domain.useCase.GetBannerUseCase
import com.example.myaplication.domain.useCase.GetCartUseCase
import com.example.myaplication.domain.useCase.GetCheckoutUseCase
import com.example.myaplication.domain.useCase.GetSpecificCategoryItems
import com.example.myaplication.domain.useCase.GetUserUseCase
import com.example.myaplication.domain.useCase.LoginUserUseCase
import com.example.myaplication.domain.useCase.UpDateUserDataUseCase
import com.example.myaplication.domain.useCase.UserProfileImageUseCase
import com.example.myaplication.domain.useCase.getCategoryInLimit
import com.example.myaplication.domain.useCase.getProductById
import com.example.myaplication.domain.useCase.getProductsInLimitUseCase
import javax.inject.Inject


class ShoppingAppViewModel @Inject constructor(

    private val createUserUseCase: CreateUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val upDateUserDataUseCase: UpDateUserDataUseCase,
    private val userProfileImageUseCase: UserProfileImageUseCase,
    private val getCategoryInLimitUseCase: getCategoryInLimit,
    private val getProductsInLimitUseCase: getProductsInLimitUseCase,
    private val addtoCardUseCase: AddtoCardUseCase,
    private val getProductByID: getProductById,
    private val addToFavUseCase: AddToFavUseCase,
    private val getAllFavUseCase: GetAllFavUseCase,
    private val getAllCategoriesUseCase: GetAllCategoryUseCase,
    private val getCheckoutUseCase: GetCheckoutUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getSpecificCategoryUseCase: GetSpecificCategoryItems,
    private val getAllSuggestedProductsUseCase: GetAllSuggestedProductsUseCase,
    private val getAllProductsUseCase: GetAllProductUseCase,
    private val getCartUseCase: GetCartUseCase

) {



}



data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserDataParent? = null
)

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class UpDateScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class uploadUserProfileImageState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class AddtoCartState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class GetProductByIDState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null
)

data class AddToFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class GetAllFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetAllProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetCartState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class AddAllCategoriesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CategoryDataModels>? = emptyList()
)

data class GetCheckoutState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null
)

data class GetSpecificCategoryItemState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetAllSuggestedProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)