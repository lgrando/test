package br.com.customapp.core

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by lucas on 05/09/18.
 */
interface EventAPI {
    @GET("events")
    fun listEvents(): Observable<JsonElement>

    @GET("events/{id}")
    fun eventDetails(@Path("id") id: String): Observable<JsonElement>

    @POST("checkinf")
    fun checkin(@Body() request: JsonObject): Observable<JsonElement>
}