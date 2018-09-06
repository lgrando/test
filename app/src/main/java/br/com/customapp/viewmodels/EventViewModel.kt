package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.widget.EditText
import br.com.customapp.models.Event
import br.com.customapp.repositories.EventRepository

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    var eventList: MutableLiveData<MutableList<Event>> = MutableLiveData()

    init {
        eventList.value = mutableListOf(
                Event("1", "Evento A", "Este é o evento A"),
                Event("2", "Evento B", "Este é o evento B"),
                Event("3", "Evento C", null)
        )
    }

    fun getEvents() {

    }
}