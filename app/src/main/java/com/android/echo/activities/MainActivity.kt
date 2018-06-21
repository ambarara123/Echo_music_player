package com.android.echo.activities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import com.android.echo.fragments.SongPlayingFragment

class MainActivity : AppCompatActivity() {

    var navDrawerIconList :ArrayList<String> = arrayListOf()

    object Statified{
        var drawerLayout :DrawerLayout?=null
        var notificationManager: NotificationManager? = null
    }

    var imageIcons = intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites
    ,R.drawable.navigation_settings,R.drawable.navigation_aboutus)

    var trackNotificationBuilder: Notification? = null
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


        val intent = Intent(this@MainActivity, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this@MainActivity, System.currentTimeMillis().toInt(),
                intent, 0)
        trackNotificationBuilder = Notification.Builder(this)
                .setContentTitle("A track is playing in background")
                .setSmallIcon(R.drawable.echo_logo)
                .setContentIntent(pIntent)
                .setOngoing(true)
                .setAutoCancel(true)
                .build()

        Statified.notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }


    override fun onStart() {
        super.onStart()
        try {

            Statified.notificationManager?.cancel(1997)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                Statified.notificationManager?.notify(1997, trackNotificationBuilder)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {

            Statified.notificationManager?.cancel(1997)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Statified.notificationManager?.cancel(1997)

    }




}