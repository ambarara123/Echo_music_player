package com.android.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.echo.R
import com.android.echo.activities.MainActivity
import com.android.echo.fragments.AboutUsFragment
import com.android.echo.fragments.FavourateFragment
import com.android.echo.fragments.MainFragment
import com.android.echo.fragments.SettingFragment

class RecyclerAdapter(contentList: ArrayList<String>, getImage: IntArray, context: Context) : RecyclerView.Adapter<RecyclerAdapter.NavViewHolder>() {


    var contentList:ArrayList<String>?=null
    var getImage :IntArray?=null
    var context : Context?=null



    init {
        this.contentList = contentList
        this.getImage = getImage
        this.context = context
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {

        var itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_nav_list,parent,false)

        var viewHolder = NavViewHolder(itemView)
        return viewHolder

    }

    override fun getItemCount(): Int {

        return (contentList as ArrayList).size
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {

        holder.navIcon?.setBackgroundResource(getImage?.get(position) as Int )
        holder.navText?.setText(contentList?.get(position))

        holder.navContainer?.setOnClickListener({
            if (position== 0){

                var mainScreenFragment = MainFragment()
                ( context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment,mainScreenFragment)
                        .commit()

            }else if (position == 1){
                var favourateFragment = FavourateFragment()
                ( context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment,favourateFragment)
                        .commit()

            }else if (position == 2){
                var settingFragment = SettingFragment()
                ( context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment,settingFragment)
                        .commit()

            } else if (position == 3) {
                var aboutUsFragment = AboutUsFragment()
                ( context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment,aboutUsFragment)
                        .commit()
            }
            MainActivity.Statified.drawerLayout?.closeDrawers()
        })
    }

    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var navIcon :ImageView?=null
        var navText :TextView?=null
        var navContainer :RelativeLayout?=null

        init {
            navIcon = itemView?.findViewById(R.id.icon_navDrawer)
            navText = itemView?.findViewById(R.id.text_navDrawer)
            navContainer = itemView?.findViewById(R.id.navigation_header_container)

        }
    }
}
