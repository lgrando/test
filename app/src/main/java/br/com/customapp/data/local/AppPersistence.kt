package br.com.customapp.data.local

import br.com.customapp.models.AppHistory
import io.paperdb.Paper

/**
 * Created by lucas on 05/09/18.
 */
class AppPersistence : Persistence {
    val APP_PERSISTENCE_KEY = "app_persistence"

    private val appBook = Paper.book()

    init {
        var appHistory: AppHistory? = read()
        if (appHistory == null) {
            write(AppHistory())
        }
    }

    override fun <T> write(item: T) {
        appBook.write(APP_PERSISTENCE_KEY, item)
    }

    override fun <T> read(): T {
        return appBook.read(APP_PERSISTENCE_KEY)
    }

    override fun clear() {
        appBook.delete(APP_PERSISTENCE_KEY)
    }
}