package br.com.customapp.core

import br.com.customapp.BuildConfig
import br.com.customapp.utils.ServiceUtil
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by lucas on 05/09/18.
 */
class ConsumerService {
    private val eventAPI = ServiceUtil.buildRetrofit(BuildConfig.ENDPOINT).create<EventAPI>(EventAPI::class.java)

    fun sendRequest(observable: Observable<JsonObject>, callback: ServiceCallback) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    callback.onSuccess(response)
                }, { e ->
                    callback.onFailure(e)
                })
    }

    fun sendRequestWithoutResponse(observable: Observable<Response<Void>>, callback: ServiceCallback) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response: Response<Void>? ->
                    if (response != null) {
                        if (response.isSuccessful) {
                            callback.onSuccess(JsonObject())
                        } else {
                            val jsonParser = JsonParser()
                            val json = jsonParser.parse(response.errorBody()?.string()).asJsonObject
                            callback.onFailure(Throwable(ServiceUtil.getErrorMessageFromJson(json)))
                        }
                    } else {
                        callback.onFailure(Throwable("Erro ao se comunicar com o servidor"))
                    }
                }
    }

    fun getEvents(callback: ServiceCallback) {
        sendRequest(eventAPI.listEvents(), callback)
    }

}