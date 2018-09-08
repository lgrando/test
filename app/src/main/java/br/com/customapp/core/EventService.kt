package br.com.customapp.core

import br.com.customapp.BuildConfig
import br.com.customapp.utils.ServiceUtil
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by lucas on 05/09/18.
 */
class EventService {
    private val eventAPI = ServiceUtil.buildRetrofit(BuildConfig.ENDPOINT).create<EventAPI>(EventAPI::class.java)

    fun sendRequest(observable: Observable<JsonElement>, callback: ServiceCallback) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    callback.onSuccess(response)
                }, { e ->
                    callback.onFailure(e)
                })
    }

    fun getEvents(callback: ServiceCallback) {
        sendRequest(eventAPI.listEvents(), callback)
    }

    fun getEventDetail(id: String, callback: ServiceCallback) {
        sendRequest(eventAPI.eventDetails(id), callback)
    }

    fun doCheckin(json: JsonObject, callback: ServiceCallback) {
        sendRequest(eventAPI.checkin(json), callback)
    }

}