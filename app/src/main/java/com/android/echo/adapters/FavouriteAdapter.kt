package com.android.echo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.echo.R
import com.android.echo.Songs
import com.android.echo.fragments.SongPlayingFragment

class FavouriteAdapter(_songDetail: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {
    var arrayList: ArrayList<Songs>? = null
    var context: Context? = null

    init {
        this.arrayList = _songDetail
        this.context = _context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        if (arrayList == null) {
            return 0
        } else {
            return (arrayList as ArrayList<Songs>).size

        }

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var songObject = arrayList?.get(position)

        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist

        holder.contentHolder?.setOnClickListener({

            val songPlayingFragment = SongPlayingFragment()
            var args = Bundle()
            args.putString("songArtist", songObject?.artist)
            args.putString("path", songObject?.songData)
            args.putString("songTitle", songObject?.songTitle)
            args.putInt("songId", songObject?.songID?.toInt() as Int)
            args.putInt("position", position)
            args.putParcelableArrayList("songData", arrayList)
            songPlayingFragment.arguments = args
            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_fragment, songPlayingFragment)
                    .addToBackStack("FavouriteFragment")
                    .commit()

        })

    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackArtist = itemView?.findViewById(R.id.trackArtist)
            trackTitle = itemView?.findViewById(R.id.trackTitle)
            contentHolder = itemView?.findViewById(R.id.contentRow)
        }

    }

}