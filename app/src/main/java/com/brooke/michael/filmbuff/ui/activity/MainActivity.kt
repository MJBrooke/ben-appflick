package com.brooke.michael.filmbuff.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.changeFragment
import com.brooke.michael.filmbuff.extensions.clear
import com.brooke.michael.filmbuff.extensions.closeKeyboard
import com.brooke.michael.filmbuff.extensions.startSearchActivity
import com.brooke.michael.filmbuff.ui.fragment.MainFragment
import com.brooke.michael.filmbuff.ui.fragment.WatchlistFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return
            }
            changeFragment{ add(R.id.fragment_container, MainFragment()) }
        }

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.setCheckedItem(R.id.nav_movies)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val myActionMenuItem = menu.findItem( R.id.searchView)
        val searchView = myActionMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    closeKeyboard()
                    startSearchActivity(query)
                    searchView.clear()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
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
