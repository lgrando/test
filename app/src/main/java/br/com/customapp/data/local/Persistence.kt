package br.com.customapp.data.local

/**
 * Created by lucas on 05/09/18.
 */
interface Persistence {
    fun <T> write(item: T)
    fun <T> read(): T
    fun clear()
}