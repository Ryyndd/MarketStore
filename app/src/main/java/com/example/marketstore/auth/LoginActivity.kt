package com.example.marketstore.auth

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.marketstore.R

class LoginActivity : AppCompatActivity() {

    // deklarasi untuk memanggil/import fungsi navController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    // membuat navController untuk handling navigasi di login
    override fun onSupportNavigateUp(): Boolean {
        // memanggil fungsi navcontroller  dari login activity
        navController = findNavController(R.id.navGrapLogin)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}