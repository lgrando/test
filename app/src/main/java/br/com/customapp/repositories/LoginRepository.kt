package br.com.customapp.repositories

import br.com.customapp.core.ConsumerService
import br.com.customapp.core.ServiceCallback
import br.com.customapp.data.local.AppPersistence
import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
class LoginRepository {
    var appPersistence = AppPersistence()
    var service = ConsumerService()

    fun startLogin(email: String, callback: ServiceCallback) {
        service.startLogin(email, callback)
    }
}