package br.com.customapp.repositories

import br.com.customapp.core.ConsumerService
import br.com.customapp.core.ServiceCallback
import br.com.customapp.data.local.AppPersistence

/**
 * Created by lucas on 05/09/18.
 */
class EventRepository {
    var appPersistence = AppPersistence()
    var service = ConsumerService()

    fun getEvents(callback: ServiceCallback) {
        
    }
}