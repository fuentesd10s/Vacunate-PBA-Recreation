package com.fuentescreations.vacunatepbarecreation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.ActivityMainBinding
import com.fuentescreations.vacunatepbarecreation.ui.fragments.*
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.myAccountFragment,
                R.id.homeFragment,
                R.id.myVaccinesFragment,
                R.id.myDatesFragment,
                R.id.trackingFragment,
                R.id.vaccinationCertificateFragment,
                R.id.notificationFragment-> {
                    hideNotificationIcon()
                    showTopBarAndNavigationDrawer()
                }
                else -> hideTopBarAndNavigationDrawer()

            }

            if (destination.id == R.id.homeFragment) showNotificationIcon()
        }

        b.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notification -> {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToNotificationFragment())
                    true
                }
                else -> false
            }
        }

        b.topAppBar.setNavigationOnClickListener {
            b.drawerLayout.open()
        }

        setupNavigationView()

        setupLogOutAction()
    }

    private fun setupLogOutAction() {
        b.navView.menu.getItem(b.navView.menu.size()-1).setOnMenuItemClickListener {

            navController.navigate(R.id.signUpFragment)

            b.drawerLayout.close()
            false
        }
    }

    private fun setupNavigationView() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.myAccountFragment,
                R.id.myVaccinesFragment,
                R.id.myDatesFragment,
                R.id.vaccinationCertificateFragment,
                R.id.trackingFragment
            ), b.drawerLayout
        )

        b.topAppBar.setupWithNavController(navController, appBarConfiguration)
        b.navView.setupWithNavController(navController)
    }

    private fun hideNotificationIcon() {
        b.topAppBar.menu.getItem(0).isVisible = false
    }

    private fun showNotificationIcon() {
        b.topAppBar.menu.getItem(0).isVisible = true
    }

    private fun showTopBarAndNavigationDrawer() {
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        b.topAppBar.show()
    }

    private fun hideTopBarAndNavigationDrawer() {
        b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        b.topAppBar.hide()
    }

    override fun onBackPressed() {
        if (b.drawerLayout.isDrawerOpen(GravityCompat.START))
            b.drawerLayout.close()
        else super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}