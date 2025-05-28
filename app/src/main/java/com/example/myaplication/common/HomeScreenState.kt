package com.example.myaplication.common

import com.example.myaplication.domain.models.BannerDataModels
import com.example.myaplication.domain.models.CategoryDataModels
import com.example.myaplication.domain.models.ProductDataModels

data class HomeScreenState (

    val isLoading : Boolean = true,
    val errorMessage : String? = null,
    val categories : List<CategoryDataModels>? = null,
    val products : List<ProductDataModels>? = null,
    val banner : List<BannerDataModels>? = null
)