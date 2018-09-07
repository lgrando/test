package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.com.customapp.core.ConsumerService
import br.com.customapp.core.ServiceCallback
import br.com.customapp.models.Event
import br.com.customapp.models.People
import com.google.gson.Gson
import com.google.gson.JsonArray

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel : ViewModel() {

    private val service = ConsumerService()
    var eventList: MutableLiveData<MutableList<Event>> = MutableLiveData()
    var peopleList: MutableLiveData<MutableList<People>> = MutableLiveData()
    var selectedEvent: MutableLiveData<Event> = MutableLiveData()

    var title = ObservableField<String>("")
    var description = ObservableField<String>("")
    var listVisibility = ObservableBoolean()
    var checkinCount = ObservableField<String>("")

    fun getEvents() {
        service.getEvents(object : ServiceCallback {
            override fun onSuccess(response: JsonArray) {
                var gson = Gson()
                val eventArray: ArrayList<Event> = ArrayList()
                for (i in 0 until response.size()) {
                    var event = gson.fromJson<Event>(response.get(i), Event::class.java)
                    eventArray.add(event)
                }
                eventList.value = eventArray.toMutableList()
            }

            override fun onFailure(error: Throwable) {
            }
        })
    }

    fun setEvent(event: Event) {
        selectedEvent.value = event
        peopleList.value = event.people?.toMutableList()
        checkinCount.set(event.people?.size.toString())
        listVisibility.set(event.people?.size!! > 0)
    }

}