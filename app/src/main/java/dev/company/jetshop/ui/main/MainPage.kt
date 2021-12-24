package dev.company.jetshop.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.company.jetshop.R

class MainPage: AppCompatActivity() {

    private var bottomnav: BottomNavigationView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottomnav = findViewById (R.id.bottomnav)

        val navController = findNavController (R.id.mainpage_fragment)

        val appBarConfiguration = AppBarConfiguration (setOf(R.id.navigation_home, R.id.navigation_cateogory, R.id.navigation_scan,R.id.navigation_account))
        setupActionBarWithNavController (navController, appBarConfiguration)
        bottomnav?.setupWithNavController (navController)
    }
}