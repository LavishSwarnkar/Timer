package com.lavish.timer.feature.timer.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TimerActionButton(
    icon: Int,
    disabledIcon: Int = icon,
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(80.dp)
            .clip(CircleShape)
            .run {
                if (enabled)
                    clickable { onClick() }
                else
                    this
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(
                if (enabled) icon else disabledIcon
            ),
            contentDescription = label
        )
    }
}