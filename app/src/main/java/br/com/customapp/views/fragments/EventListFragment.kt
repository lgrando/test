package br.com.customapp.views.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.customapp.R
import br.com.customapp.databinding.FragmentEventListBinding
import br.com.customapp.viewmodels.EventViewModel
import br.com.customapp.views.adapters.EventAdapter
import kotlinx.android.synthetic.main.fragment_event_list.*

class EventListFragment : Fragment() {

    private lateinit var viewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter
//    var eventAdapter = EventAdapter(ArrayList(), this.activity!!) {
//
//        val event = viewModel.eventList.value?.get(it)
//
//        event?.let { event ->
//            Toast.makeText(activity, "Cliquei no evento [" + event.id + "]", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentEventListBinding>(
                inflater,
                R.layout.fragment_event_list,
                container,
                false
        )

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(EventViewModel::class.java)
            binding.viewModel = viewModel
        }

        binding.handler = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLiveDataObserver()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        eventAdapter = EventAdapter(ArrayList(), this.activity!!) {
            val event = viewModel.eventList.value?.get(it)
            event?.let { ev ->
                Toast.makeText(activity, "Cliquei no evento [" + ev.id + "]", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = eventAdapter
    }

    private fun setupLiveDataObserver() {
        viewModel.eventList.observe(this, Observer {
            if (it != null) {
                eventAdapter.updateList(it)
            }
        })
    }

}
