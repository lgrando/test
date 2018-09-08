package br.com.customapp.models

data class Event(
        val id: String,
        val title: String,
        val latitude: String,
        val longitude: String,
        val image: String?,
        val description: String?,
        val date: Long,
        val people: ArrayList<People>?
)