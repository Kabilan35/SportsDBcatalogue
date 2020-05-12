package com.example.sportsdb.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.sportsdb.MoshiClass
import com.example.sportsdb.dataDetails
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.sportsdb.R
import com.squareup.picasso.Picasso
import java.util.logging.Logger


class DescAdapter(val Items: List<MoshiClass.Team>) : RecyclerView.Adapter<ViewHolder>() {
    val log = Logger.getLogger(MoshiClass::class.java.getName())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.desc_view, parent, false)
        println("DescAdapter")
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataDetails!!.size
    }

    override fun onBindViewHolder(holderDesc: ViewHolder, position: Int) {
        println("onBindViewHolder DescList")
        val TeamInfo = dataDetails!![position]
        holderDesc.descr.text = "Description: " + TeamInfo.Description
        holderDesc.stad.text = "Stadium: " + TeamInfo.Stadium
        holderDesc.stadCap.text = "Stadium Capacity: " + TeamInfo.StadiumCapacity
        Picasso.get().load(TeamInfo.Banner+"/preview").fit().centerInside()
            .into(holderDesc.banner)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    internal val banner: ImageView = view.findViewById(R.id.banner)
    internal val descr: AppCompatTextView = view.findViewById(R.id.Desc)
    internal val stad: AppCompatTextView = view.findViewById(R.id.stadium)
    internal val stadCap: AppCompatTextView = view.findViewById(R.id.stadiumCap)
}