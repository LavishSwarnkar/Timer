package com.lavish.timer.feature.timer.comp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import com.lavish.timer.feature.timer.Timer
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TimerView(
    modifier: Modifier,
    timerStateFlow: StateFlow<Timer>
) {
    val timer = timerStateFlow.collectAsState()

    TimerNotificationHelper(timer)

    val ringForegroundColor = MaterialTheme.colorScheme.primary
    val ringBackgroundColor = Color.Black.copy(alpha = 0.1f)

    Box(
        modifier = modifier
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        val sweepAngle = animateFloatAsState(targetValue = -360f * timer.value.portionLeft, label = "Sweep")

        // Ring
        Canvas(
            modifier = Modifier.fillMaxSize(0.8f)
        ) {
            rotate(-90f) {
                drawArc(
                    color = ringBackgroundColor,
                    size = size,
                    style = Stroke(15f),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false
                )
                drawArc(
                    color = ringForegroundColor,
                    size = size,
                    style = Stroke(15f),
                    startAngle = 360f,
                    sweepAngle = sweepAngle.value,
                    useCenter = false
                )
            }
        }

        // Time Text
        Text(
            text = timer.value.time,
            style = MaterialTheme.typography.displayMedium
        )
    }
}