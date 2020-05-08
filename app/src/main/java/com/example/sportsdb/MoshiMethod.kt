package com.example.sportsdb

import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MoshiMethod {

        @GET("/api/v1/json/1/searchplayers.php")
        //https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?p=Danny%20Welbeck
        fun queryPlayer(@Query("p") name: String): Call<MoshiClass.info>

        @GET("/api/v1/json/1/search_all_teams.php")
        //https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
        fun queryTeams(@Query("l") name: String): Call<MoshiClass.teams>

}
