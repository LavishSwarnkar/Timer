package com.lavish.timer.feature.timer.comp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lavish.timer.feature.timer.Timer

@Composable
fun TimerComp(
    modifier: Modifier,
    timer: Timer
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timer.time,
            style = MaterialTheme.typography.displayMedium
        )
    }
}