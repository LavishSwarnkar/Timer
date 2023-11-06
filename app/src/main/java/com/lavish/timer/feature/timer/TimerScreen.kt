package com.lavish.timer.feature.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lavish.timer.feature.timer.comp.TimerActions
import com.lavish.timer.feature.timer.comp.TimerView
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TimerView(
            modifier = Modifier
                .fillMaxWidth(),
            timerStateFlow = viewModel.stateFlow
        )

        Spacer(modifier = Modifier.size(50.dp))

        TimerActions(
            modifier = Modifier.fillMaxWidth(),
            viewModel = viewModel
        )
    }
}