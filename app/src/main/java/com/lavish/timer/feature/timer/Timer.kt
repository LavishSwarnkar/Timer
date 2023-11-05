package com.lavish.timer.feature.timer

import com.lavish.timer.util.TimeFormatter

data class Timer(
    val state: State = State.NEW,
    val duration: Long = 1 * 60 * 1000,
    val tickInMillis: Long = duration
) {
    val time: String = TimeFormatter.formatTime(tickInMillis)

    val percentageLeft: Double =
        (tickInMillis / duration.toDouble()) * 100

    enum class State {
        NEW, RUNNING, PAUSED, COMPLETE
    }

    fun tick(): Timer {
        return if (tickInMillis == 1L) {
            Timer(
                tickInMillis = 0,
                state = State.COMPLETE
            )
        } else {
            Timer(
                tickInMillis = tickInMillis - 1,
                state = State.RUNNING
            )
        }
    }

    fun pause(): Timer {
        return copy(
            state = State.PAUSED
        )
    }

    fun stop(): Timer {
        return Timer()
    }
}