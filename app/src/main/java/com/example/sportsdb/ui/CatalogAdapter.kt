package com.example.sportsdb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsdb.MoshiClass
import com.example.sportsdb.R
import com.example.sportsdb.dataDetails
import com.squareup.picasso.Picasso


class CatalogAdapter(team_list: List<MoshiClass.Team>) :
    RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {
    class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal val sectionRecycler: RecyclerView = itemView.findViewById(R.id.section_recycler)
    internal val sectionLabel: AppCompatTextView = itemView.findViewById(R.id.section_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.horizontal, parent, false)
        println("onCreateViewHolder")
        return CatalogViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataDetails!!.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        println("onBindViewHolder")
        val Teamname = dataDetails!![position]
        val TeamInfo = dataDetails
            TeamInfo?.takeIf { dataDetails!!.isNotEmpty() }?.let {
            holder.sectionRecycler.run {
                holder.sectionLabel.text = Teamname.Team
                val context = holder.itemView.context
                val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = mLayoutManager
                isNestedScrollingEnabled = false
                if (itemDecorationCount == 0) {
                    val itemPadding = context.resources.getDimensionPixelSize(R.dimen.default_padding)
                    addItemDecoration(RecyclerViewCellMaringDecoration(itemPadding))
                }
                setHasFixedSize(true)
                adapter = SectionAdapter(TeamInfo)
            }
        }
    }
}