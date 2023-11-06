package com.lavish.timer.feature.timer

import com.lavish.timer.util.TimeFormatter

data class Timer(
    val state: State = State.NEW,
    val end: Long? = null,
    val tickInMillis: Long = DURATION
) {
    val time: String = TimeFormatter.formatTime(tickInMillis)

    val portionLeft: Float = tickInMillis / DURATION.toFloat()

    enum class State {
        NEW, RUNNING, PAUSED, COMPLETE
    }

    fun tick(): Timer {
        val newEnd = end ?: (System.currentTimeMillis() + tickInMillis)
        val diff = newEnd - System.currentTimeMillis()

        return if (diff <= 1L) {
            Timer(
                tickInMillis = 0,
                state = State.COMPLETE
            )
        } else {
            copy(
                tickInMillis = diff,
                state = State.RUNNING,
                end = System.currentTimeMillis() + diff
            )
        }
    }

    fun pause(): Timer {
        return copy(
            state = State.PAUSED,
            end = null
        )
    }

    fun stop(): Timer {
        return Timer()
    }

    override fun toString(): String {
        return "Timer(state=$state, end=$end, tickInMillis=$tickInMillis, time='$time', percentageLeft=$portionLeft)"
    }

    companion object {
//        const val DURATION = 1 * 60 * 1000L
        const val DURATION = 15 * 1000L
    }
}