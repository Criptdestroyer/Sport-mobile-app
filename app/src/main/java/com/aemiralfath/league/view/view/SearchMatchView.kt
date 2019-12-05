package com.aemiralfath.league.view.view

import com.aemiralfath.league.model.response.SearchResponse


interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(dataMatch: SearchResponse)
}