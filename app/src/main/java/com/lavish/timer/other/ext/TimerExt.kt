package com.lavish.timer.other.ext

import com.lavish.timer.R
import com.lavish.timer.feature.timer.Timer
import com.lavish.timer.feature.timer.Timer.State.COMPLETE
import com.lavish.timer.feature.timer.Timer.State.NEW
import com.lavish.timer.feature.timer.Timer.State.PAUSED
import com.lavish.timer.feature.timer.Timer.State.RUNNING

fun Timer.stateActionIcon(): Int {
    return when (state) {
        RUNNING -> R.drawable.ic_pause
        NEW, PAUSED, COMPLETE -> R.drawable.ic_resume
    }
}

fun Timer.stateActionLabel(): String {
    return when (state) {
        RUNNING -> "Pause"
        PAUSED -> "Resume"
        NEW, COMPLETE -> "Start"
    }
}

fun Timer.isRunning() = state == RUNNING

fun Timer.isRunningOrPaused() = state in listOf(RUNNING, PAUSED)

fun Timer.isNotComplete() = state != COMPLETE