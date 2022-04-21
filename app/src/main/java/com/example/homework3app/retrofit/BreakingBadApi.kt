package com.example.homework3app.retrofit

import com.example.homework3app.data.Person
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("characters")
    fun getPersons(): retrofit2.Call<List<Person>>
}

// https://www.breakingbadapi.com/api/characters