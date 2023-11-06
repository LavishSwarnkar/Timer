package com.lavish.timer.other.util

object TimeFormatter {
    fun formatTime(timeInMillis: Long): String {
        var millis = timeInMillis
        var secs = timeInMillis / 1000
        val mins = secs / 60

        millis -= secs * 1000
        secs -= mins * 60

        val minsFormatted = if (mins > 0) {
            mins.toString().padStart(2, '0')
        } else null

        val secsFormatted = secs.toString().padStart(2, '0')

        val millisFormatted = millis.toString().padStart(3, '0')

        return listOfNotNull(
            minsFormatted, secsFormatted, millisFormatted
        ).joinToString(".")
    }
}