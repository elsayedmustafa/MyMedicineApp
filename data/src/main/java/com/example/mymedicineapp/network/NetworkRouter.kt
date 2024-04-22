package com.example.mymedicineapp.network

import com.example.mymedicineapp.exception.ApiException
import com.example.mymedicineapp.exception.NoInternetException
import com.example.mymedicineapp.exception.SessionTimeOutException
import com.example.mymedicineapp.exception.UnexpectedException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class NetworkRouter @Inject constructor() {

    companion object {
        private const val SESSION_TIME_OUT_ERROR_CODE = 402
        private const val TOKEN_EXPIRED_ERROR_CODE = 401
    }

    suspend fun <T : Any> invokeApi(call: suspend () -> Response<T>): T? {
        try {
            val response = call.invoke()
            if (response.code() == TOKEN_EXPIRED_ERROR_CODE) {
                throw SessionTimeOutException()
            }
            if (response.isSuccessful ) {
                return response.body()
            }

            if (response.code() == SESSION_TIME_OUT_ERROR_CODE) {
                throw SessionTimeOutException()
            }else {
                throw UnexpectedException()
            }
        } catch (e: Exception) {
            throw when (e) {
                is IOException -> NoInternetException()
                is HttpException -> ApiException()
                is SessionTimeOutException-> throw e
                else -> UnexpectedException()
            }
        }
    }

}
