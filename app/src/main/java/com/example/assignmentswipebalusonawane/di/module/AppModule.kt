package com.example.assignmentswipebalusonawane.di.module

import android.content.Context
import com.example.assignmentswipebalusonawane.BuildConfig
import com.example.assignmentswipebalusonawane.data.remote.Api
import com.example.assignmentswipebalusonawane.data.repository.DefaultMainRepository
import com.example.assignmentswipebalusonawane.data.repository.MainRepository
import com.example.assignmentswipebalusonawane.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { provideNetworkHelper(androidContext()) }

    single { provideConverterFactory() }

    single { provideRetrofit(BuildConfig.BASE_URL, get()) }

    single { provideApi(get()) }

}

private fun provideNetworkHelper(context: Context): NetworkHelper = NetworkHelper(context)

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(Base_url: String,
                    gsonConverterFactory: GsonConverterFactory) : Retrofit = Retrofit.Builder()
    .baseUrl(Base_url)
    .addConverterFactory(gsonConverterFactory)
    .build()

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)



