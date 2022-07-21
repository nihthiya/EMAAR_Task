package com.example.emaartask.di

import android.app.Application
import androidx.multidex.BuildConfig
import androidx.room.Room
import com.example.emaartask.data.apiServices.UserListAPI
import com.example.emaartask.data.network.UserListApiHelper
import com.example.emaartask.data.network.UserListApiHelperImpl
import com.example.emaartask.database.Database
import com.example.emaartask.database.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://randomuser.me"

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideUserListApiService(retrofit: Retrofit): UserListAPI = retrofit.create(UserListAPI::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: UserListApiHelperImpl): UserListApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDataBase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, "EMAAR_TASK_DB")
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: Database): UserDAO {
        return dataBase.userDAO
    }
}