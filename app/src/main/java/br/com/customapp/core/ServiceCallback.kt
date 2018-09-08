package br.com.customapp.core

import com.google.gson.JsonElement

/**
 * Created by lucas on 05/09/18.
 */
interface ServiceCallback {
    fun onSuccess(response: JsonElement)
    fun onFailure(error: Throwable)
}