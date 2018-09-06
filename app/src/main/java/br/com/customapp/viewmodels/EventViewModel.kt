package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.customapp.models.EventItem
import br.com.customapp.repositories.EventRepository

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel : ViewModel() {

    var eventList: MutableLiveData<MutableList<EventItem>> = MutableLiveData()

    init {
        eventList.value = mutableListOf<EventItem>(
                EventItem(1, "Evento A", "Este é o evento A"),
                EventItem(2, "Evento B", "Este é o evento B"),
                EventItem(3, "Evento C", null)
        )
    }

    fun getEvents() {

    }
}