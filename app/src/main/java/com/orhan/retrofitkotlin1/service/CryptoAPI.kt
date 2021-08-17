package com.orhan.retrofitkotlin1.service

import com.orhan.retrofitkotlin1.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //http://api.nomics.com/v1/prices?key=f35b5008feba80ff17bb88acdaeaaaea712a97ea
    @GET("prices?key=f35b5008feba80ff17bb88acdaeaaaea712a97ea")
    fun getData(): Call<List<CryptoModel>>
}