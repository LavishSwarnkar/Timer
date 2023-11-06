package com.lavish.timer.other.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lavish.timer.main.MainActivity
import com.lavish.timer.R
import java.util.Random

class NotificationUtil(
    private val context: Context
) {

    companion object {
        const val DEFAULT_CHANNEL = "notifications"

        fun areNotificationsEnabled(context: Context): Boolean {
            val notificationManager = NotificationManagerCompat.from(context)
            return notificationManager.areNotificationsEnabled()
        }

        @Composable
        fun PermissionsSetup() {

            val context = LocalContext.current

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { granted ->
                        if (!granted) {
                            Toast.makeText(context, "Notifications won't be shown!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )

                LaunchedEffect(Unit) {
                    if (areNotificationsEnabled(context)) return@LaunchedEffect

                    launcher.launch(
                        android.Manifest.permission.POST_NOTIFICATIONS
                    )
                }
            }
        }
    }

    private var lastNotificationId: Int? = null

    private fun createNotificationChannel(
        id: String = DEFAULT_CHANNEL,
        name: String = "All Notifications",
        description: String = "Chat and Assignment related notifications"
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(NotificationManager::class.java)

            val alreadyExists = notificationManager.notificationChannels.find { it.id == id } != null
            if (alreadyExists) return

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)
            channel.description = description
            channel.enableVibration(true)

            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showNotification(
        title: String,
        body: String,
        channelId: String = DEFAULT_CHANNEL,
        modifyIntent: Intent.() -> Unit = {}
    ) {
        if (!areNotificationsEnabled(context)) {
            Log.w(this::class.simpleName, "PostNotification permission not granted!")
            return
        }

        createNotificationChannel(channelId)

        /* PendingIntent */
        val intent = Intent(context, MainActivity::class.java).apply { modifyIntent() }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT xor PendingIntent.FLAG_IMMUTABLE)

        /* Build */
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setSmallIcon(R.drawable.ic_timer)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationId = Random().nextInt(30000) + 1000

        /* Post */
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notification)

        lastNotificationId = notificationId
    }

    fun hideLastShownNotification() {
        lastNotificationId?.let { id ->
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.cancel(id)
            lastNotificationId = null
        }
    }
}