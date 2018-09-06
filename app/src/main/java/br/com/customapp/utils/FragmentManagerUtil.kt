package br.com.customapp.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import br.com.customapp.R

/**
 * Created by lucas on 06/09/18.
 */
class FragmentManagerUtil(val supportFragmentManager: FragmentManager) {

    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_content, fragment, tag)
                .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

}