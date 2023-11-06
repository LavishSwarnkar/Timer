package com.lavish.timer.feature.timer.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.lavish.timer.R
import com.lavish.timer.feature.timer.Timer.State.*
import com.lavish.timer.feature.timer.TimerViewModel
import com.lavish.timer.other.ext.isRunningOrPaused
import com.lavish.timer.other.ext.stateActionIcon
import com.lavish.timer.other.ext.stateActionLabel

@Composable
fun TimerActions(
    modifier: Modifier,
    viewModel: TimerViewModel
) {
    val timer = viewModel.stateFlow.collectAsState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (timer.value.state == COMPLETE) {

            // Restart
            TimerActionButton(
                icon = R.drawable.ic_restart,
                label = "Restart",
                onClick = viewModel::restart
            )
        } else {

            // Resume / Pause
            TimerActionButton(
                icon = timer.value.stateActionIcon(),
                label = timer.value.stateActionLabel(),
                onClick = {
                    when (timer.value.state) {
                        RUNNING -> viewModel.pause()
                        NEW, PAUSED, COMPLETE -> viewModel.resume()
                    }
                }
            )

            // Stop
            TimerActionButton(
                icon = R.drawable.ic_stop_enabled,
                disabledIcon = R.drawable.ic_stop_disabled,
                label = "Stop",
                enabled = timer.value.isRunningOrPaused(),
                onClick = viewModel::stop
            )
        }
    }
}