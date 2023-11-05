package com.lavish.timer.feature.timer.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lavish.timer.R
import com.lavish.timer.feature.timer.Timer
import com.lavish.timer.feature.timer.Timer.State.*
import com.lavish.timer.feature.timer.TimerViewModel
import com.lavish.timer.feature.timer.ext.isNotComplete
import com.lavish.timer.feature.timer.ext.isRunning
import com.lavish.timer.feature.timer.ext.isRunningOrPaused
import com.lavish.timer.feature.timer.ext.stateActionIcon
import com.lavish.timer.feature.timer.ext.stateActionLabel

@Composable
fun TimerActions(
    modifier: Modifier,
    viewModel: TimerViewModel
) {
    val timer = viewModel.state.value

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (timer.state == COMPLETE) {

            // Restart
            TimerActionButton(
                icon = R.drawable.ic_restart,
                label = "Restart",
                onClick = viewModel::restart
            )
        } else {

            // Resume / Pause
            TimerActionButton(
                icon = timer.stateActionIcon(),
                label = timer.stateActionLabel(),
                onClick = {
                    when (timer.state) {
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
                enabled = timer.isRunningOrPaused(),
                onClick = viewModel::stop
            )
        }
    }
}