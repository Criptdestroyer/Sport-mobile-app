package com.aemiralfath.league.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.league.R
import com.aemiralfath.league.model.db.Favorite
import org.jetbrains.anko.find

class FavoriteAdapter(
    private val favorite: List<Favorite>,
    private val listener: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return FavoriteHolder(mView)
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}

class FavoriteHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val eventName: TextView = view.find(R.id.tv_item_title)
    private val eventDate: TextView = view.find(R.id.tv_item_date)

    @SuppressLint("SetTextI18n")
    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        eventName.text = favorite.eventName
        eventDate.text = favorite.eventDate
        itemView.setOnClickListener { listener(favorite) }
    }
}