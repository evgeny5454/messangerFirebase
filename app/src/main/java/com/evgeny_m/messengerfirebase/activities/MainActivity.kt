package com.evgeny_m.messengerfirebase.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny_m.messengerfirebase.databinding.ActivityMainBinding
import com.evgeny_m.messengerfirebase.utils.initNavDrawer

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initNavDrawer() // инициализация DrawerLayout
    }
}