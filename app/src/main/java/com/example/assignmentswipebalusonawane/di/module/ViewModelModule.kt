package com.example.assignmentswipebalusonawane.di.module

import com.example.assignmentswipebalusonawane.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(),get())
    }

}