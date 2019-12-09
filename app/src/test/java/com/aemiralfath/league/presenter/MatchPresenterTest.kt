package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.TestContextProvider
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.response.TeamResponse
import com.aemiralfath.league.view.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getMatchDetail() {
        val responseMatch = MatchResponse(listOf())
        val responseHome = TeamResponse(arrayListOf())
        val responseAway = TeamResponse(arrayListOf())

        val idEvent= "602279"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(responseMatch)
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(responseHome)
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(responseAway)

            presenter.getMatchDetail(idEvent)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailMatch(responseMatch, responseHome,responseAway)
            Mockito.verify(view).hideLoading()
        }
    }
}