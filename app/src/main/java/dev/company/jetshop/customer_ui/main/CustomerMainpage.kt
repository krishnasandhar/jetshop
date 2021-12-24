package dev.company.jetshop.customer_ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.company.jetshop.R

class CustomerMainpage: AppCompatActivity() {

    private var bottomnav: BottomNavigationView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_mainpage)

        bottomnav = findViewById (R.id.customerbottomnav)

        val navController = findNavController (R.id.customermainpage_fragment)

        val appBarConfiguration = AppBarConfiguration (setOf( R.id.nav_customerhome, R.id.nav_customercateogory, R.id.nav_customerscan, R.id.nav_customeraccount))
        setupActionBarWithNavController (navController, appBarConfiguration)
        bottomnav?.setupWithNavController (navController)
    }
}