package com.fuentescreations.vacunatepbarecreation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuentescreations.vacunatepbarecreation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.topAppBar.setNavigationOnClickListener {
            b.drawerLayout.open()
        }
    }
}