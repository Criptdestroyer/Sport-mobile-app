package com.aemiralfath.league

import java.net.URL

class ApiRepository {
    fun doRequest(url: String) = URL(url).readText();
}