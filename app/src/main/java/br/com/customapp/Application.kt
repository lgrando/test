package br.com.customapp

import android.app.Application
import io.paperdb.Paper

/**
 * Created by lucas on 05/09/18.
 */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Paper.init(applicationContext)
    }
}