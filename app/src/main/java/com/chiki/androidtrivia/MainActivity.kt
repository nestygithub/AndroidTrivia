package com.chiki.androidtrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.chiki.androidtrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        if (navHostFragment!=null){
            val navController = navHostFragment.findNavController()
            NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
            NavigationUI.setupWithNavController(binding.navView,navController)
            navController.addOnDestinationChangedListener { controller, destination, _ ->
                if(destination.id == controller.graph.startDestination){
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }else{
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }
    }

    //Navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}