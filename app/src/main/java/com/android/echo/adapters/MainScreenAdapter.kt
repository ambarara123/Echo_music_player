package com.android.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.android.echo.R
import com.android.echo.Songs

class MainScreenAdapter(_songDetail: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {
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
        val songObject = arrayList?.get(position)

        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist

        holder.contentHolder?.setOnClickListener({
            Toast.makeText(context, "Hey " + songObject?.songTitle, Toast.LENGTH_SHORT).show()
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