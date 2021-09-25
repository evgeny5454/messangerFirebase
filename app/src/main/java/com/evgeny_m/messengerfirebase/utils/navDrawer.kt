package com.evgeny_m.messengerfirebase.utils

import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.evgeny_m.messengerfirebase.MainActivity
import com.evgeny_m.messengerfirebase.R


lateinit var drawerLayout: DrawerLayout

fun MainActivity.initNavDrawer() {
    drawerLayout = binding.drawerLayout
    val navView = binding.navView
    val navController = findNavController(R.id.nav_content_host)
    var appBarConfiguration = AppBarConfiguration(setOf(R.menu.menu_nav_drawer),drawerLayout)
    navView.setupWithNavController(navController)
}

fun initGamburgerButton(toolbar:Toolbar) {
    toolbar.setNavigationIcon(R.drawable.icon_gamburger_button)
    toolbar.setNavigationOnClickListener {
        drawerLayout.open()
    }
}