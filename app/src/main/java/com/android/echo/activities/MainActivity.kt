package com.android.echo.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.android.echo.R
import com.android.echo.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    var drawerLayout :DrawerLayout?=null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()


        val mainScreenFragment = MainFragment()
        this.supportFragmentManager.beginTransaction()
                .add(R.id.detail_fragment, mainScreenFragment,"MainScreenFragment")
                .commit()

        var navigation_recyclerView = findViewById<RecyclerView>(R.id.nav_recyclerView)
        navigation_recyclerView.layoutManager = LinearLayoutManager(this)
        navigation_recyclerView.itemAnimator = DefaultItemAnimator()


    }


    override fun onStart() {
        super.onStart()
    }
}
