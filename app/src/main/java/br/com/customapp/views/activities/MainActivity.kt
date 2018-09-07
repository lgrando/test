package br.com.customapp.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.customapp.R
import br.com.customapp.databinding.ActivityMainBinding
import br.com.customapp.utils.Constants
import br.com.customapp.utils.FragmentManagerUtil
import br.com.customapp.viewmodels.EventViewModel
import br.com.customapp.views.fragments.EventListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventViewModel
    private val fragmentManager = FragmentManagerUtil(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.handler = this

        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        fragmentManager.replaceFragment(EventListFragment(), Constants.TAG_EVENT_LIST_FRAGMENT)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        fragmentManager.replaceFragment(fragment, tag)
    }
}
