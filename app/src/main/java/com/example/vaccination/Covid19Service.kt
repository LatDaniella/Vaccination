package com.example.vaccination

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Covid19Service {
    @GET("vaccine/coverage/countries")
    fun getVaccinations(@Query("lastdays") lastdays : Int) : Call<List<VaccinationInfo>>
    // The @Query is a helper for retrofit that allows you to call any number

}