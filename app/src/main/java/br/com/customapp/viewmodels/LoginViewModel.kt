package br.com.customapp.viewmodels

import android.arch.lifecycle.ViewModel
import android.widget.EditText
import br.com.customapp.core.ServiceCallback
import br.com.customapp.repositories.LoginRepository
import com.google.gson.JsonObject

/**
 * Created by lucas on 05/09/18.
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun startLogin(etUser: EditText) {
        loginRepository.startLogin(etUser.text.toString(), object : ServiceCallback {
            override fun onSuccess(response: JsonObject) {

            }

            override fun onFailure(error: Throwable) {

            }

        })
    }
}