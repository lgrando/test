package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import br.com.customapp.core.ConsumerService
import br.com.customapp.core.ServiceCallback
import br.com.customapp.models.EventItem
import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel : ViewModel() {

    private val service = ConsumerService()
    var phoneNumber = ObservableField<String>("")
    var eventList: MutableLiveData<MutableList<EventItem>> = MutableLiveData()

    init {
        eventList.value = mutableListOf<EventItem>(
                EventItem(1, "Evento A", "Este é o evento A"),
                EventItem(2, "Evento B", "Este é o evento B"),
                EventItem(3, "Evento C", null)
        )
    }

    fun getEvents() {
        service.getEvents(object : ServiceCallback{
            override fun onSuccess(response: JsonObject) {

            }

            override fun onFailure(error: Throwable) {

            }
        })
    }
}