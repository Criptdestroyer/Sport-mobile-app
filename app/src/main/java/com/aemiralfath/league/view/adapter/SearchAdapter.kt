package com.aemiralfath.league.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.league.R
import com.aemiralfath.league.model.item.MatchItem
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.style
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter(
    private val items: List<MatchItem>,
    private val listener: (MatchItem) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LeagueAdapterUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val name = containerView.find<TextView>(R.id.name_league)
        private val dateTime = containerView.find<TextView>(R.id.date_time)

        fun bindItem(items: MatchItem, listener: (MatchItem) -> Unit) {
            name.text = items.eventName
            dateTime.text = toGMTFormat(items.eventDate, items.eventTime)
            itemView.setOnClickListener {
                listener(items)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun toGMTFormat(date: String?, time: String?): String? {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("WIB")

            val dateTime: String = when {
                date == null -> "00-00-00 $time"
                time == null -> "$date 00:00:00"
                else -> "$date $time"
            }

            return "${formatter.parse(dateTime)}"
        }
    }

    class LeagueAdapterUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.name_league
                    textSize = 16f
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }

                textView {
                    id = R.id.date_time
                    textSize = 13f
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }


            }

        }

    }
}