package br.com.customapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
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
    var peopleList: MutableLiveData<MutableList<People>> = MutableLiveData()
    var selectedEvent: MutableLiveData<Event> = MutableLiveData()

    var listEventsResponse = SingleLiveEvent<Void>()
    var onCheckinResponse = SingleLiveEvent<Void>()
    var onCheckinError = ObservableInt(View.GONE)

    var eventList = ObservableField<MutableList<Event>>()
    var emptyList = ObservableInt(View.GONE)

    var title = ObservableField<String>("")
    var description = ObservableField<String>("")
    var eventLoaded = ObservableInt(View.GONE)
    var checkinCount = ObservableInt()
    var doCheckin = ObservableInt(View.GONE)
    var nameError = ObservableInt(View.GONE)
    var emailError = ObservableInt(View.GONE)

    var loading = ObservableInt(View.GONE)
    var checkinLoading = ObservableBoolean(false)

    fun setEvent(event: Event) {
        getEventDetail(event.id)
    }

    fun getEvents() {
        loading.set(View.VISIBLE)
        repository.getEvents(object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                loading.set(View.GONE)
                var gson = Gson()
                val eventArray: ArrayList<Event> = ArrayList()

                for (i in 0 until response.asJsonArray.size()) {
                    var event = gson.fromJson<Event>(response.asJsonArray.get(i), Event::class.java)
                    eventArray.add(event)
                }
                eventList.set(eventArray.toMutableList())

                if (eventList.get().size < 1) {
                    emptyList.set(View.VISIBLE)
                } else {
                    emptyList.set(View.GONE)
                }
                listEventsResponse.call()
            }

            override fun onFailure(error: Throwable) {
                loading.set(View.GONE)
                emptyList.set(View.VISIBLE)
            }
        })
    }

    /*
      A chama /events já retornava todas informações necessárias para popular a tela EventDetailFragment.
      Chamada /events/{id} foi criada para atingir todas as especificações do teste
    */
    fun getEventDetail(id: String) {
        loading.set(View.VISIBLE)
        eventLoaded.set(View.GONE)
        repository.getEventDetail(id, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                loading.set(View.GONE)
                var gson = Gson()
                var event = gson.fromJson<Event>(response.asJsonObject, Event::class.java)
                selectedEvent.value = event
                peopleList.value = event.people?.toMutableList()
                event.people?.size?.let { checkinCount.set(it) }
                eventLoaded.set(View.VISIBLE)
            }

            override fun onFailure(error: Throwable) {
                loading.set(View.GONE)
            }
        })
    }

    fun onClickCheckin(view: View) {
        if (doCheckin.get() == View.VISIBLE) {
            doCheckin.set(View.GONE)
        } else {
            doCheckin.set(View.VISIBLE)
            onCheckinError.set(View.GONE)
        }
    }

    fun checkinRequest(name: String, email: String) {
        checkinLoading.set(true)
        repository.checkinRequest(selectedEvent.value?.id, name, email, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                checkinLoading.set(false)
                onCheckinError.set(View.GONE)
                doCheckin.set(View.GONE)
                onCheckinResponse.call()
            }

            override fun onFailure(error: Throwable) {
                checkinLoading.set(false)
                onCheckinError.set(View.VISIBLE)
            }
        })
    }

}