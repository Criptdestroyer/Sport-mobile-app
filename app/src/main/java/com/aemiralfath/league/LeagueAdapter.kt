package com.aemiralfath.league

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeagueAdapter(private val context: Context, private val items: List<Item>) :
        RecyclerView.Adapter<LeagueAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LeagueAdapterUI().createView(AnkoContext.Companion.create(parent.context, parent)))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val name = view.find<TextView>(R.id.name_league)
        private val description = view.find<TextView>(R.id.description_league)
        private val image = view.find<ImageView>(R.id.image_league)

        fun bindItem(items: Item){
            name.text = items.name
            description.text = items.description
            items.image?.let { Picasso.get().load(it).fit().into(image) }
        }
    }

    class LeagueAdapterUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            verticalLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView{
                    id = R.id.image_league
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                verticalLayout{
                    lparams(width = wrapContent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)

                    textView{
                        id = R.id.name_league
                        textSize = 16f
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
                        margin = dip(10)
                    }

                    textView{
                        id = R.id.description_league
                        textSize = 14f
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
                        margin = dip(10)
                        bottom = R.id.name_league
                    }



                }
            }
        }

    }
}