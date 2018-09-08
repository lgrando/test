package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import br.com.customapp.core.ServiceCallback
import br.com.customapp.models.Event
import br.com.customapp.models.People
import br.com.customapp.repositories.EventRepository
import com.google.gson.Gson
import com.google.gson.JsonElement

/**
 * Created by lucas on 05/09/18.
 */
class EventViewModel : ViewModel() {

    private val repository = EventRepository()
    var eventList: MutableLiveData<MutableList<Event>> = MutableLiveData()
    var peopleList: MutableLiveData<MutableList<People>> = MutableLiveData()
    var selectedEvent: MutableLiveData<Event> = MutableLiveData()
    var onCheckinResponse: MutableLiveData<Boolean> = MutableLiveData()
    var callCheckinDialog = SingleLiveEvent<Void>()

    var title = ObservableField<String>("")
    var description = ObservableField<String>("")
    var listSize = ObservableBoolean()
    var checkinCount = ObservableField<String>("")

    fun setEvent(event: Event) {
        getEventDetail(event.id)
    }

    fun getEvents() {
        repository.getEvents(object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                var gson = Gson()
                val eventArray: ArrayList<Event> = ArrayList()

                for (i in 0 until response.asJsonArray.size()) {
                    var event = gson.fromJson<Event>(response.asJsonArray.get(i), Event::class.java)
                    eventArray.add(event)
                }
                eventList.value = eventArray.toMutableList()
            }

            override fun onFailure(error: Throwable) {

            }
        })
    }

    /*
      A chama /events já retornava todas informações necessárias para popular a tela EventDetailFragment.
      Chamada /events/{id} foi criada para atingir todas as especificações do teste
    */
    fun getEventDetail(id: String) {
        repository.getEventDetail(id, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                var gson = Gson()
                var event = gson.fromJson<Event>(response.asJsonObject, Event::class.java)
                selectedEvent.value = event
                peopleList.value = event.people?.toMutableList()
                checkinCount.set(event.people?.size.toString())
                listSize.set(event.people?.size!! > 0)
            }

            override fun onFailure(error: Throwable) {

            }
        })
    }

    fun onClickCheckin(view: View) {
        callCheckinDialog.call()
    }

    fun doCheckin(name: String, email: String) {
        repository.doCheckin(selectedEvent.value?.id, name, email, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                onCheckinResponse.value = true
            }

            override fun onFailure(error: Throwable) {
                onCheckinResponse.value = false
            }
        })
    }

}