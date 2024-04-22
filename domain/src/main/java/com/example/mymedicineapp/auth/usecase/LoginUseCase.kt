package com.example.mymedicineapp.auth.usecase
import com.example.mymedicineapp.auth.model.LoginDomainModel
import javax.inject.Inject


class LoginUseCase @Inject constructor() {
    suspend operator fun invoke(username: String): LoginDomainModel {
       return LoginDomainModel(username)

    }
}
