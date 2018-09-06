package br.com.customapp.core

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by lucas on 05/09/18.
 */
interface EventAPI {
    @POST("listEvents")
    fun listEvents(@Body() request: JsonObject): Observable<JsonObject>
}