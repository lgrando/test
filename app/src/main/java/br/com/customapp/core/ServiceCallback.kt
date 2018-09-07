package br.com.customapp.core

import com.google.gson.JsonArray

/**
 * Created by lucas on 05/09/18.
 */
interface ServiceCallback {
    fun onSuccess(response: JsonArray)
    fun onFailure(error: Throwable)
}