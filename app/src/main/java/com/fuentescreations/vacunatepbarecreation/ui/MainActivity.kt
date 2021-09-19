package com.fuentescreations.vacunatepbarecreation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

                R.id.summaryFragment ->{
                    showTopBarAndNavigationDrawer()
                }
                R.id.myAccountFragment ->{
                    showTopBarAndNavigationDrawer()
                }

                else ->{
                    hideTopBarAndNavigationDrawer()
                }
            }
        }

        b.navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()
                }
                R.id.myAccount -> {
                    Toast.makeText(this, "My Account", Toast.LENGTH_SHORT).show()
                }
                R.id.myDates -> {
                    Toast.makeText(this, "My dates", Toast.LENGTH_SHORT).show()
                }
                R.id.myVaccines -> {
                    Toast.makeText(this, "My Vaccines", Toast.LENGTH_SHORT).show()
                }
                R.id.tracking -> {
                    Toast.makeText(this, "Tracking", Toast.LENGTH_SHORT).show()
                }
                R.id.logOut -> {
                    Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                }
            }

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
    }

    private fun showTopBarAndNavigationDrawer(){
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        b.topAppBar.show()
    }
    private fun hideTopBarAndNavigationDrawer(){
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        b.topAppBar.hide()
    }
}