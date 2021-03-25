package com.meadowlandsapps.clouds

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Formats this Double as a String with the hard pattern "#0.0"
 *
 * @return the formatted Double as a String in the pattern "#0.0"
 */
fun Double.toDecimalDisplayString(): String = DecimalFormat("#0.0").format(this)

/**
 * Formats the time as a human readable string in the form "MM:DD:YYYY:hh:mm:ss"
 */
fun Long.timeToDisplayString(): String {
    val calendar = GregorianCalendar.getInstance()
    calendar.timeInMillis = this
    val timeFormatter = SimpleDateFormat.getDateTimeInstance()
    timeFormatter.calendar = calendar
    return timeFormatter.format(calendar.time)
}

fun Int.windBearingToWindDirection(): String {
    return when (this) {
        in 45..134 -> "E"
        in 135..224 -> "S"
        in 225..314 -> "W"
        in 315..360 -> "N"
        in 0..44 -> "N"
        else -> ""
    }
}
