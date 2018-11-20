package com.iav.kade.rest

import com.iav.kade.model.ResponseModel
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

interface ApiService {

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String): Observable<ResponseModel>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Observable<ResponseModel>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Observable<ResponseModel>

    @GET("lookup_all_teams.php")
    fun getTeamByLigaId(@Query("id") id: String): Observable<ResponseModel>

    @GET("lookup_all_players.php")
    fun playerByIdTeam(@Query("id") id: String): Observable<ResponseModel>

    @GET("lookupplayer.php?id=34160896")
    fun detailPlayer(@Query("id") id: String): Observable<ResponseModel>

    @GET("searchevents.php")
    fun searchEvent(@Query("e") id: String): Observable<ResponseModel>
    @GET("searchteams.php")
    fun searchTeam(@Query("t") id: String): Observable<ResponseModel>
}