package com.aemiralfath.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    class MainActivityUI: AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout{
                recyclerView{
                    lparams(width= matchParent,height = matchParent)
                    layoutManager = LinearLayoutManager(context)
//                    adapter =
                }
            }
        }



    }
}
