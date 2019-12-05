package com.aemiralfath.league.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.aemiralfath.league.R
import com.aemiralfath.league.model.item.MatchItem
import com.aemiralfath.league.view.adapter.MatchAdapter

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment(private val mData: ArrayList<MatchItem>?) : Fragment() {
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_match, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_match)

        matchAdapter = MatchAdapter()
        matchAdapter.notifyDataSetChanged()

        mData?.let { matchAdapter.setData(it) }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = matchAdapter

        return rootView
    }


}
