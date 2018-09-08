package br.com.customapp.repositories

import br.com.customapp.core.EventService
import br.com.customapp.core.ServiceCallback
import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
class EventRepository {
    var service = EventService()

    fun getEvents(callback: ServiceCallback) {
        service.getEvents(callback)
    }

    fun getEventDetail(id: String, callback: ServiceCallback) {
        service.getEventDetail(id, callback)
    }

    fun checkinRequest(eventId: String?, name: String, email: String, callback: ServiceCallback) {
        val json = JsonObject()
        json.addProperty("eventId", eventId)
        if (name.isNotEmpty()) {
            json.addProperty("name", name)
        }
        if (email.isNotEmpty()) {
            json.addProperty("email", email)
        }

        service.checkinRequest(json, callback)
    }
}