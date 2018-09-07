package br.com.customapp.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.customapp.R
import br.com.customapp.databinding.FragmentEventDetailBinding
import br.com.customapp.utils.GeocoderUtil
import br.com.customapp.viewmodels.EventViewModel

class EventDetailFragment : Fragment() {

    private lateinit var viewModel: EventViewModel

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
        val location: Location = Location("")
        location.latitude = -30.0392981
        location.longitude = -51.2146267
        GeocoderUtil.getLocationDetails(location, this.activity!!)
    }

}
