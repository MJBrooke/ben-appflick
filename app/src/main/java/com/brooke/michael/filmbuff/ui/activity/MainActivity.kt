package com.brooke.michael.filmbuff.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.changeFragment
import com.brooke.michael.filmbuff.ui.fragment.MainFragment
import com.brooke.michael.filmbuff.ui.fragment.WatchlistFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return
            }
            changeFragment{ add(R.id.fragment_container, MainFragment()) }
        }

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.setCheckedItem(R.id.nav_movies)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_settings -> true
            else -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment: Fragment? = when (item.itemId) {
            R.id.nav_movies -> MainFragment()
            R.id.nav_to_watch -> WatchlistFragment()
            else -> null
        }

        fragment?.let {
            changeFragment { replace(R.id.fragment_container, fragment) }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
