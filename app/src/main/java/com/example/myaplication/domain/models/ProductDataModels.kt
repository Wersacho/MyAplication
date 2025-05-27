package com.example.myaplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDataModels(

    val name : String = "",
    val description : String = "",
    val price : String = "",
    val finalPrice : String = "",
    val category : String = "",
    val image : String = "",
    val date : String = "",
    val createBy : String = "",
    val availableUnits : Int = 0,
    var productId : String = ""

)
