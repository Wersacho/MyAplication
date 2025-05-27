package com.example.myaplication.domain.useCase


import android.net.Uri
import com.example.myaplication.common.ResultState
import com.example.myaplication.domain.models.CartDataModels
import com.example.myaplication.domain.models.ProductDataModels
import com.example.myaplication.domain.models.UserData
import com.example.myaplication.domain.models.UserDataParent
import com.example.myaplication.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileImageUseCase @Inject constructor(private val repo: Repo) {

    fun userProfileImage(uri: Uri): Flow<ResultState<String>>{

        return repo.userProfileImage(uri)
    }
}