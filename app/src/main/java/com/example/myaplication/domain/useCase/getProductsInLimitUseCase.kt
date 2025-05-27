package com.example.myaplication.domain.useCase


import com.example.myaplication.common.ResultState
import com.example.myaplication.domain.models.CartDataModels
import com.example.myaplication.domain.models.ProductDataModels
import com.example.myaplication.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getProductsInLimitUseCase @Inject constructor(private val repo: Repo) {

    fun getProductInLimit(): Flow<ResultState<List<ProductDataModels>>>{

        return repo.getProductsInLimited()
    }
}