package com.example.vaccination

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

 // https://disease.sh/v3/covid-19/
interface Covid19Service {
    @GET("vaccine/coverage/countries")
    fun getVaccinations(@Query("lastdays") lastdays : Int) : Call<List<VaccinationInfo>>
    // The @Query is a helper for retrofit that allows you to call any number

    @GET("all")
    fun getWorldwide(@Query("lastdays") lastdays : Int) : Call<List<VaccinationInfo>>

}