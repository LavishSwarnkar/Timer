package com.lavish.timer.feature.timer.comp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.lavish.timer.feature.timer.Timer
import com.lavish.timer.feature.timer.Timer.Companion.DURATION_TEXT
import com.lavish.timer.feature.timer.Timer.State.COMPLETE
import com.lavish.timer.other.util.NotificationUtil
import com.lavish.timer.other.ext.OnPauseEffect
import com.lavish.timer.other.ext.OnResumeEffect
import org.koin.compose.getKoin

@Composable
fun TimerNotificationHelper(
    timer: State<Timer>,
    notificationsHelper: NotificationUtil = getKoin().get()
) {
    NotificationUtil.PermissionsSetup()

    val showNotification = remember { mutableStateOf(true) }

    OnPauseEffect { showNotification.value = true }

    OnResumeEffect { showNotification.value = false }

    LaunchedEffect(key1 = timer.value.state) {
        if (timer.value.state == COMPLETE && showNotification.value) {

            notificationsHelper.showNotification(
                title = "Timer stopped",
                body = "Your $DURATION_TEXT timer has been stopped"
            )
        } else {
            notificationsHelper.hideLastShownNotification()
        }
    }
}