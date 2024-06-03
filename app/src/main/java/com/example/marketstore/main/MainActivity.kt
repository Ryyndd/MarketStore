package com.example.marketstore.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.marketstore.R
import com.example.marketstore.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var productFragment: ProductFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation
        homeFragment = HomeFragment()
        productFragment = ProductFragment()
        profileFragment = ProfileFragment()

        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.product -> {
                    loadFragment(ProductFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main,fragment)
        transaction.commit()
    }

    // membuat navController untuk handling navigasi di login
    override fun onSupportNavigateUp(): Boolean {
        // memanggil fungsi navcontroller  dari product activity
        navController = findNavController(R.id.nav_grap_product)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}

