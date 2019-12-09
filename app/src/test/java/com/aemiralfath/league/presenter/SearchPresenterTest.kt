package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.TestContextProvider
import com.aemiralfath.league.model.item.MatchItem
import com.aemiralfath.league.model.response.SearchResponse
import com.aemiralfath.league.view.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun searchMatch() {
        val search: ArrayList<MatchItem> = ArrayList()
        val response = SearchResponse(search)
        val query = "liverpool"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", SearchResponse::class.java)).thenReturn(response)

            presenter.searchMatch(query)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailMatch(response)
            Mockito.verify(view).hideLoading()
        }

    }
}