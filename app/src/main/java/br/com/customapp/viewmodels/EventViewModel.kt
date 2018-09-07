package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import br.com.customapp.core.ConsumerService
import br.com.customapp.core.ServiceCallback
import br.com.customapp.models.Event
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel : ViewModel() {

    private val service = ConsumerService()
    var phoneNumber = ObservableField<String>("")
    var eventList: MutableLiveData<MutableList<Event>> = MutableLiveData()

    init {
        eventList.value = mutableListOf<Event>(/*
                Event(1, "Evento A", "Este é o evento A"),
                Event(2, "Evento B", "Este é o evento B"),
                Event(3, "Evento C", null)*/
        )
    }

    fun getEvents() {
        service.getEvents(object : ServiceCallback {
            override fun onSuccess(response: JsonArray) {
                var gson = Gson()
                val eventList2: ArrayList<Event> = ArrayList()
                for (i in 0 until response.size()) {
                    var event = gson.fromJson<Event>(response.get(i), Event::class.java)
                    eventList2.add(event)
                }
                eventList.value = eventList2.toMutableList()
            }

            override fun onFailure(error: Throwable) {
            }
        })
    }
}