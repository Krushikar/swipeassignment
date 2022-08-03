package com.example.assignmentswipebalusonawane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentswipebalusonawane.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}