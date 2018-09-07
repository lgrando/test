package br.com.customapp.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.*

object GeocoderUtil {

    fun getLocationDetails(latitude: Double, longitude: Double, context: Context): Address {
        val geocoder = Geocoder(context, Locale.getDefault())
        val locationDetails = geocoder.getFromLocation(latitude, longitude, 1)
        val address = locationDetails[0] as Address
        return address
    }

}