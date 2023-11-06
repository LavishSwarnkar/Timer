package com.lavish.timer.feature.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    private val mutableStateFlow = MutableStateFlow(Timer())
    val stateFlow: StateFlow<Timer> = mutableStateFlow

    private lateinit var tickingJob: Job

    fun resume() {
        tickingJob = viewModelScope.launch {
            while (true) {
                val newState = mutableStateFlow.value.tick()
                mutableStateFlow.emit(newState)

                if (newState.state == Timer.State.COMPLETE) break

                delay(1)
            }
        }
    }

    fun pause() {
        tickingJob.cancel()
        viewModelScope.launch {
            mutableStateFlow.emit(mutableStateFlow.value.pause())
        }
    }

    fun stop() {
        tickingJob.cancel()
        viewModelScope.launch {
            mutableStateFlow.emit(mutableStateFlow.value.stop())
        }
    }

    fun restart() {
        mutableStateFlow.value = Timer()
        resume()
    }

}