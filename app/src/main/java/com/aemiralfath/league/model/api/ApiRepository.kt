package com.aemiralfath.league.model.api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String) = URL(url).readText();
}