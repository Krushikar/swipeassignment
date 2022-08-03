package com.example.assignmentswipebalusonawane.di.module

import com.example.assignmentswipebalusonawane.data.remote.Api
import com.example.assignmentswipebalusonawane.data.repository.DefaultMainRepository
import com.example.assignmentswipebalusonawane.data.repository.MainRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val repoModule = module {

    single { provideMainRepository(get()) }

}


fun provideMainRepository(api: Api): MainRepository = DefaultMainRepository(api)