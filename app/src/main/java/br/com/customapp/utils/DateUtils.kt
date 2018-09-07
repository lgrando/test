package br.com.customapp.utils

import java.text.SimpleDateFormat

object DateUtils {

    private var simpleDateFormat = SimpleDateFormat()

    fun getFormattedDate(dateLong: Long?) : String {
        val format = "MMM dd"
        simpleDateFormat = SimpleDateFormat(format, Constants.PT_BR_LOCALE)
        return simpleDateFormat.format(dateLong).toUpperCase()
    }

    fun getFormattedTime(dateLong: Long?) : String {
        val format = "HH:mm"
        simpleDateFormat = SimpleDateFormat(format, Constants.PT_BR_LOCALE)
        return simpleDateFormat.format(dateLong)
    }
}