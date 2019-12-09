package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.TestContextProvider
import com.aemiralfath.league.model.item.DetailLeagueItem
import com.aemiralfath.league.model.item.MatchItem
import com.aemiralfath.league.model.response.DetailLeagueResponse
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.view.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.*

class LeaguePresenterTest {

    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: LeaguePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getLeagueDetail() {
        val responseLeague = DetailLeagueResponse(listOf())
        val responsePreviousMatch = MatchResponse(arrayListOf())
        val responseNextMatch = MatchResponse(arrayListOf())

        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", DetailLeagueResponse::class.java)).thenReturn(responseLeague)
            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(responsePreviousMatch)
            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(responseNextMatch)

            presenter.getLeagueDetail(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailLeague(responseLeague, responsePreviousMatch,responseNextMatch)
            Mockito.verify(view).hideLoading()
        }
    }
}