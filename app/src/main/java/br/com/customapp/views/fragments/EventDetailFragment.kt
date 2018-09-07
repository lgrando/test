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
import br.com.customapp.R
import br.com.customapp.databinding.FragmentEventDetailBinding
import br.com.customapp.models.Event
import br.com.customapp.utils.Constants
import br.com.customapp.utils.DateUtils
import br.com.customapp.utils.GeocoderUtil
import br.com.customapp.viewmodels.EventViewModel
import br.com.customapp.views.activities.MainActivity
import br.com.customapp.views.adapters.EventAdapter
import br.com.customapp.views.adapters.PeopleAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event_detail.*
import kotlinx.android.synthetic.main.fragment_event_list.*

class EventDetailFragment : Fragment() {

    private lateinit var viewModel: EventViewModel
    private lateinit var peopleAdapter: PeopleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentEventDetailBinding>(
                inflater,
                R.layout.fragment_event_detail,
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
        viewModel.selectedEvent.observe(this@EventDetailFragment, Observer {
            it?.let {
                setupEventDetails(it)
            }
        })
    }

    private fun setupRecyclerView() {
        peopleList.layoutManager = LinearLayoutManager(activity)
        peopleAdapter = PeopleAdapter(ArrayList(), this.activity!!)
        peopleList.adapter = peopleAdapter
    }

    private fun setupLiveDataObserver() {
        viewModel.peopleList.observe(this, Observer {
            if (it != null) {
                peopleAdapter.updateList(it)
            }
        })
    }

    private fun setupEventDetails(it: Event) {
        var geolocation = GeocoderUtil.getLocationDetails(it.latitude.toDouble(), it.longitude.toDouble(), this.activity!!)

        title.text = it.title
        description.text = it.description?.trim()
        Picasso.get().load(it.image).resize(600,400).centerCrop().into(image)
        date.text = DateUtils.getFormattedDate(it.date)
        time.text = DateUtils.getFormattedTime(it.date)
        address.text = geolocation.thoroughfare + ", " + geolocation.subThoroughfare
        city.text = geolocation.subAdminArea
    }

}
