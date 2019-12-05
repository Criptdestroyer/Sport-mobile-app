package com.aemiralfath.league.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.league.R
import com.aemiralfath.league.model.item.MatchItem
import com.aemiralfath.league.view.activity.DetailMatchActivity
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.DataViewHolder>() {
    private val mData = ArrayList<MatchItem>()

    fun setData(items: ArrayList<MatchItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return DataViewHolder(mView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(mData[position])

        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailMatchActivity::class.java)
            intent.putExtra("EXTRA_ID", mData[position].eventId)
            intent.putExtra("EXTRA_NAME", mData[position].eventName)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtTitle: TextView = view.findViewById(R.id.tv_item_title)
        private val txtDate: TextView = view.findViewById(R.id.tv_item_date)

        fun bind(dataItems: MatchItem) {
            txtTitle.text = dataItems.eventName
            txtDate.text = toGMTFormat(dataItems.eventDate, dataItems.eventTime)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormat(date: String?, time: String?): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("WIB")

        val dateTime: String = when {
            date == null -> "0000-00-00 $time"
            time == null -> "$date 00:00:00"
            else -> "$date $time"
        }

        return "${formatter.parse(dateTime)}"
    }
}
