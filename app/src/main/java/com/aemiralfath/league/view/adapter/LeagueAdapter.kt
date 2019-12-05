package com.aemiralfath.league.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.league.R
import com.aemiralfath.league.model.item.LeagueItem
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

class LeagueAdapter(
    private val items: List<LeagueItem>,
    private val listener: (LeagueItem) -> Unit
) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

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
        private val image = containerView.find<ImageView>(R.id.image_league)

        fun bindItem(items: LeagueItem, listener: (LeagueItem) -> Unit) {
            name.text = items.name
            items.image?.let { Picasso.get().load(it).fit().placeholder(it).error(it).into(image) }
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    class LeagueAdapterUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                imageView {
                    id = R.id.image_league
                }.lparams {
                    height = dip(150)
                    width = matchParent
                    bottomMargin = dip(16)
                }


                textView {
                    id = R.id.name_league
                    textSize = 14f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                    bottom = R.id.image_league
                }


            }

        }

    }
}