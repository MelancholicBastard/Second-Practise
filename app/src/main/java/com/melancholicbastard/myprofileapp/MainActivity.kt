package com.melancholicbastard.myprofileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.melancholicbastard.myprofileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        val viewModelka = ViewModelProvider(this)[MainViewModel::class.java]
        viewModelka.putContext(this)

        binding.viewModel1 = viewModelka
        binding.lifecycleOwner = this

    }
}