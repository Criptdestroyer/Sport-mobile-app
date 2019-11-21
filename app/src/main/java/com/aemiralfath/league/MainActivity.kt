package com.aemiralfath.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueAdapter(items){

                }
            }
        }
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val description = resources.getStringArray(R.array.league_description)
        val image = resources.obtainTypedArray(R.array.league_image)
        items.clear()

        for (i in id.indices) {
            items.add(Item(id[i], name[i], description[i], image.getResourceId(i, 0)))
        }
        image.recycle()
    }

}
