package com.example.myaplication.domain.useCase

import com.example.myaplication.common.ResultState
import com.example.myaplication.domain.models.CartDataModels
import com.example.myaplication.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddtoCardUseCase @Inject constructor(private val repo: Repo) {

    fun addtoCard(cartDataModels: CartDataModels): Flow<ResultState<String>>{

        return repo.addToCart(cartDataModels)
    }
}