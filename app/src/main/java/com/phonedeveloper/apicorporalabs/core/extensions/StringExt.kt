package com.phonedeveloper.apicorporalabs.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */
fun isNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}

//Obtener milisegundos a Hora Completa
fun String.getDiffTimeHoursMinutesSeconds(diffMilliseconds: Long): String {
    return String.format(
        "%02d%02d%02ds",
        TimeUnit.MILLISECONDS.toHours(diffMilliseconds),
        TimeUnit.MILLISECONDS.toMinutes(diffMilliseconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffMilliseconds)), // The change is in this line
        TimeUnit.MILLISECONDS.toSeconds(diffMilliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diffMilliseconds))
    )
}

/**
 * This function formats the date returned from news api to a more readable format e.g (2021/12/21)
 */

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK)
    val outputFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.UK)
    try {
        return outputFormat.format(inputFormat.parse(date) ?: "")
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun String?.convertMilliSec(): Long {
    if (this.isNullOrBlank()) return 0
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.US)
    return try {
        val date: Date = format.parse(this)
        date.time
    } catch (e: ParseException) {
        0
    }
}

private val DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

// Extension function to Calendar
fun Calendar.toDateString(): String {
    return DATE_FORMAT.format(time)
}

fun String.toCalendar(): Calendar? {
    return DATE_FORMAT.parse(this)?.toCalendar()
}

fun Date.toCalendar(): Calendar {
    return Calendar.getInstance().also {
        it.time = this
    }
}

fun Date.formattedWith(pattern: String): String? {
    return try {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        simpleDateFormat.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.dateTimeFormatted(): String? {
    return formattedWith("EEE dd, MMMM YYYY")
}

fun Date.dayFormatted(): String? {
    return formattedWith("dd")
}

fun Date.monthFormatted(): String? {
    return formattedWith("MMM")?.substring(0,3)
}

fun Date.yearFormatted(): String? {
    return formattedWith("YYYY")
}

fun Date.timeFormatted(): String? {
    return formattedWith("HH:mm a")
}