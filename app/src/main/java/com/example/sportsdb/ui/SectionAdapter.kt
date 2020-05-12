package com.example.sportsdb.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsdb.MoshiClass
import com.example.sportsdb.R
import com.example.sportsdb.dataDetails
import com.squareup.picasso.Picasso
import java.util.logging.Logger


class SectionAdapter(val Items: List<MoshiClass.Team>) : RecyclerView.Adapter<ItemViewHolder>() {
    val log = Logger.getLogger(MoshiClass::class.java.getName())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_view, parent, false)
        println("SectionAdapter")
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataDetails!!.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val TeamInfo = dataDetails!![position]
        holder.label.text = TeamInfo.Team
        holder.subLabel.text = TeamInfo.TeamShort
        println("onBindViewHolder Section")
        Picasso.get().load(TeamInfo.TeamBadge+"/preview").fit().centerInside()
            .into(holder.imageView)
        holder.imageView.setOnClickListener(View.OnClickListener {
            println("Clicked $TeamInfo.Team")
        })
    }

    }

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    internal val imageView: ImageView = view.findViewById(R.id.card_image)
    internal val label: AppCompatTextView = view.findViewById(R.id.card_label)
    internal val subLabel: AppCompatTextView = view.findViewById(R.id.card_sub_label)
}
