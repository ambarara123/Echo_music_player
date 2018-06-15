package com.android.echo.activities

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.android.echo.R
import com.android.echo.adapters.RecyclerAdapter
import com.android.echo.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    var navDrawerIconList :ArrayList<String> = arrayListOf()

    object Statified{
        var drawerLayout :DrawerLayout?=null
    }

    var imageIcons = intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites
    ,R.drawable.navigation_settings,R.drawable.navigation_aboutus)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        navDrawerIconList.add("All Songs")
        navDrawerIconList.add("Favourites")
        navDrawerIconList.add("Settings")
        navDrawerIconList.add("About Us")

        MainActivity.Statified.drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this@MainActivity,MainActivity.Statified.drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        MainActivity.Statified.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        val mainScreenFragment = MainFragment()
        this.supportFragmentManager.beginTransaction()
                .add(R.id.detail_fragment, mainScreenFragment,"MainScreenFragment")
                .commit()

        var adapter = RecyclerAdapter(navDrawerIconList,imageIcons,this)
        adapter.notifyDataSetChanged()

        var navigation_recyclerView = findViewById<RecyclerView>(R.id.nav_recycler_view)
        navigation_recyclerView.layoutManager = LinearLayoutManager(this)
        navigation_recyclerView.itemAnimator = DefaultItemAnimator()


        navigation_recyclerView.adapter = adapter
        navigation_recyclerView.setHasFixedSize(true)

    }


    override fun onStart() {
        super.onStart()
    }
}