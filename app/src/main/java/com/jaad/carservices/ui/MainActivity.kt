package com.jaad.carservices.ui

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jaad.carservices.R
import com.jaad.carservices.ui.base.BaseActivity
import com.jaad.carservices.ui.home.HomeViewModel

class MainActivity : BaseActivity<HomeViewModel>() {

    override val layoutRes = R.layout.activity_main

    override fun setUp() {

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}