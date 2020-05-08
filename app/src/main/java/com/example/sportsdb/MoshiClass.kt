package com.example.sportsdb

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.logging.Logger

var dataDetails: List<MoshiClass.Team>? = null

class MoshiClass() {
    //var dataDetails = MutableLiveData<ArrayList<MoshiClass.teams>>()
    var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun startConvert(param1: String) {
        val log = Logger.getLogger(MoshiClass::class.java.getName())
        log.info("Search Item received in MoshiClass")
        val input = param1
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<MoshiClass.teams> = moshi.adapter(MoshiClass.teams::class.java)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().let {
                it.create(MoshiMethod::class.java)
            }
        runBlocking {
        retrofit.queryTeams(input).enqueue(object : Callback<MoshiClass.teams> {
            override fun onResponse(call: Call<MoshiClass.teams>, response: Response<MoshiClass.teams>) {
                //log.info("Successful Query: " + response.body())
                val respo = response.body()
                if (respo != null) {
                    parseTeams(respo)
                }
                executorService.shutdown()
            }
            override fun onFailure(call: Call<MoshiClass.teams>, t: Throwable) {
                log.info("Failed Call: $t")
                executorService.shutdown()
            }
        }) }
//        retrofit.queryPlayer(input).enqueue(object : Callback<MoshiClass.info> {
//            override fun onResponse(call: Call<MoshiClass.info>, response: Response<MoshiClass.info>) {
//                log.info("input: " + input)
//                log.info("Successful Query: " + response.body())
//                val dataDetails = response.body()
//                val result = adapter.toJson(dataDetails)
//                println(result)
//            }
//            override fun onFailure(call: Call<MoshiClass.info>, t: Throwable) {
//                log.info("Failed Call: $t")
//            }
//        })
    }

    fun parseTeams(teams: teams) {
        dataDetails = teams.teams
        println(dataDetails)
    }

    @JsonClass(generateAdapter = true)
    data class Details(
        @Json(name = "strNationality") val Nationality: String?= null,
        @Json(name = "strPlayer") val Name: String?= null,
        @Json(name = "strTeam") val Team: String?= null,
        @Json(name = "dateBorn") val DOB: String?= null,
        @Json(name = "strWage") val Wage: String?= null,
        @Json(name = "strPosition") val PlayingPosition: String?= null,
        @Json(name = "strHeight") val Height: String?= null,
        @Json(name = "strWeight") val Weight: String?= null,
        @Json(name = "strThumb") val image: String?= null
    )
    @JsonClass(generateAdapter = true)
    data class info(
        @Json(name="player") val info: List<Details>? = null
    )
    @JsonClass(generateAdapter = true)
    data class teams(
        @Json(name="teams") val teams: List<Team>
    )
    @JsonClass(generateAdapter = true)
    data class Team(
        @Json(name = "strStadium") val Stadium: String?= null,
        @Json(name = "strLeague") val League: String?= null,
        @Json(name = "strTeam") val Team: String?= null,
        @Json(name = "strDescriptionEN") var Description: String? = null,
        @Json(name = "strTeamBanner") var Banner: String? = null,
        @Json(name = "strTeamShort") var TeamShort: String? = null,
        @Json(name = "strTeamBadge") var TeamBadge: String? = null
    )
}