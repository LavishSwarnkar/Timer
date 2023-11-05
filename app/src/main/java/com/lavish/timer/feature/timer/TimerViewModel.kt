package com.lavish.timer.feature.timer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lavish.timer.feature.timer.ext.isNotComplete
import com.lavish.timer.feature.timer.ext.isRunning
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    val state = mutableStateOf(Timer())

    private lateinit var tickingJob: Job

    fun resume() {
        tickingJob = viewModelScope.launch {
            while (state.value.isNotComplete()) {
                state.value = state.value.tick()
                Log.i("MTT", "${state.value}")
                delay(1)
            }
        }
    }

    fun pause() {
        tickingJob.cancel()
        state.value = state.value.pause()
        Log.i("MTT", "${state.value}")
    }

    fun stop() {
        tickingJob.cancel()
        state.value = state.value.stop()
        Log.i("MTT", "${state.value}")
    }

}