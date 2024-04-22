package com.example.mymedicineapp.di

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.mymedicineapp.database.MedicinesAppDatabase
import com.example.mymedicineapp.database.MedicinesAppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://run.mocky.io/v3/"
    private const val ROOM_DATABASE_NAME = "medicines_db"
    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun headersInterceptor(@ApplicationContext context: Context): Interceptor = Interceptor { chain ->
        var request = chain.request()
        try {

            request = request.newBuilder()
                .header("Accept", "application/json")
                .build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
        hInterceptor: Interceptor
    ): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        trustAllBypassCertificationLogic(httpClient)

        return httpClient
            .addInterceptor(hInterceptor)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .also {
                    it.addInterceptor(interceptor)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun trustAllBypassCertificationLogic(httpCLient: OkHttpClient.Builder) {

        try {
            val x509TrustManager: X509TrustManager = @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                    // "Not yet implemented")
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                    // "Not yet implemented")
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            }
            val trustAllCerts: Array<TrustManager> = arrayOf(x509TrustManager)
            val sc: SSLContext = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, SecureRandom())
            httpCLient.sslSocketFactory(sc.socketFactory, x509TrustManager)
            httpCLient.hostnameVerifier(HostnameVerifier { _, _ -> true })
        } catch (ignored: Exception) {
            Log.e("CERTIFICATE - " , ignored.message.toString())
        }
    }

    @Singleton
    @Provides
    fun provideMedicinesAppDao(medicinesDatabase: MedicinesAppDatabase): MedicinesAppDao = medicinesDatabase.medicinesAppDao()

    @Singleton
    @Provides
    fun provideMedicineAppDatabase(@ApplicationContext context: Context): MedicinesAppDatabase =
        Room.databaseBuilder(context, MedicinesAppDatabase::class.java, ROOM_DATABASE_NAME).fallbackToDestructiveMigration().build()
}
