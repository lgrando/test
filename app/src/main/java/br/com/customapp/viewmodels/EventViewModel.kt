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
    var onCheckinResponse: MutableLiveData<Boolean> = MutableLiveData()

    var callCheckinDialog = SingleLiveEvent<Void>()
    var listEventsResponse = SingleLiveEvent<Void>()

    var eventList = ObservableField<MutableList<Event>>()
    var title = ObservableField<String>("")
    var description = ObservableField<String>("")
    var checkinCount = ObservableInt()
    var loading = ObservableInt(View.GONE)
    var emptyList = ObservableInt(View.GONE)
    var eventLoaded = ObservableInt(View.GONE)

    fun setEvent(event: Event) {
        getEventDetail(event.id)
    }

    private fun setLoading(show: Boolean) {
        val visibility: Int = if (show) View.VISIBLE else View.GONE
        loading.set(visibility)
    }

    private fun setEmptyList(show: Boolean) {
        val visibility: Int = if (show) View.VISIBLE else View.GONE
        emptyList.set(visibility)
    }

    private fun setEventLoaded(show: Boolean) {
        val visibility: Int = if (show) View.VISIBLE else View.GONE
        eventLoaded.set(visibility)
    }

    fun getEvents() {
        setLoading(true)
        repository.getEvents(object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                setLoading(false)
                var gson = Gson()
                val eventArray: ArrayList<Event> = ArrayList()

                for (i in 0 until response.asJsonArray.size()) {
                    var event = gson.fromJson<Event>(response.asJsonArray.get(i), Event::class.java)
                    eventArray.add(event)
                }
                eventList.set(eventArray.toMutableList())
                setEmptyList(eventList.get().size < 1)
                listEventsResponse.call()
            }

            override fun onFailure(error: Throwable) {
                setLoading(false)
                setEmptyList(true)
            }
        })
    }

    /*
      A chama /events já retornava todas informações necessárias para popular a tela EventDetailFragment.
      Chamada /events/{id} foi criada para atingir todas as especificações do teste
    */
    fun getEventDetail(id: String) {
        setLoading(true)
        setEventLoaded(false)
        repository.getEventDetail(id, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                setLoading(false)
                var gson = Gson()
                var event = gson.fromJson<Event>(response.asJsonObject, Event::class.java)
                selectedEvent.value = event
                peopleList.value = event.people?.toMutableList()
                event.people?.size?.let { checkinCount.set(it) }
                setEventLoaded(true)
            }

            override fun onFailure(error: Throwable) {
                setLoading(false)
            }
        })
    }

    fun onClickCheckin(view: View) {
        callCheckinDialog.call()
    }

    fun doCheckin(name: String, email: String) {
        setLoading(true)
        repository.doCheckin(selectedEvent.value?.id, name, email, object : ServiceCallback {
            override fun onSuccess(response: JsonElement) {
                setLoading(false)
                onCheckinResponse.value = true
            }

            override fun onFailure(error: Throwable) {
                setLoading(false)
                onCheckinResponse.value = false
            }
        })
    }

}