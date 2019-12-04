package com.aemiralfath.league.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.aemiralfath.league.R
import com.aemiralfath.league.model.item.LeagueItem
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<LeagueItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = GridLayoutManager(context, 2)
                adapter = LeagueAdapter(items) {
                    startActivity<DetailLeagueActivity>("EXTRA_ITEM" to it)
                }
            }
        }
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_image)
        items.clear()

        for (i in id.indices) {
            items.add(
                LeagueItem(
                    id[i],
                    name[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        image.recycle()
    }

}
