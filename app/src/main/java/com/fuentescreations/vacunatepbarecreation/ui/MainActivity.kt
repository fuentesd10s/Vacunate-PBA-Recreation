package com.fuentescreations.vacunatepbarecreation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.ActivityMainBinding
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when(destination.id){

                R.id.summaryFragment, R.id.myAccountFragment ->{
                    showTopBarAndNavigationDrawer()
                }

                else ->{
                    hideTopBarAndNavigationDrawer()
                }
            }
        }

        b.navView.setNavigationItemSelectedListener {
            it.isChecked = true
            b.drawerLayout.close()
            true
        }

        b.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notification -> {
                    Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        b.topAppBar.setNavigationOnClickListener {
            b.drawerLayout.open()
        }
        setupDrawerLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, b.drawerLayout)
    }

    private fun setupDrawerLayout() {
        b.navView.setupWithNavController(navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, b.drawerLayout)
    }

    private fun showTopBarAndNavigationDrawer(){
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        b.topAppBar.show()
    }
    private fun hideTopBarAndNavigationDrawer(){
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        b.topAppBar.hide()
    }
    override fun onBackPressed() {
        if (b.drawerLayout.isDrawerOpen(GravityCompat.START))
            b.drawerLayout.close()
        else super.onBackPressed()

    }
}