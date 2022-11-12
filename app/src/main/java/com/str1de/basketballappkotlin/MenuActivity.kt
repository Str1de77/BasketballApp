package com.str1de.basketballappkotlin

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close)
        val availableString = " (available later...)"
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.menu.findItem(R.id.training_book_item).isVisible = false
        navigationView.menu.findItem(R.id.curry_dribbling_challenge).isVisible = false
        navigationView.menu.findItem(R.id.curry_shooting_challenge).isVisible = false
        navigationView.menu.findItem(R.id.curry_finishing_challenge).isVisible = false

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.percent_of_throws_made -> startActivity(Intent(this, TableActivity::class.java))
                R.id.position_throws -> startActivity(Intent(this, PositionThrowsActivity::class.java))
            }
               true
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}