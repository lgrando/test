package br.com.customapp.core

import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
interface ServiceCallback {
    fun onSuccess(response: JsonObject)
    fun onFailure(error: Throwable)
}