package com.huawei.android.app

import android.app.AndroidAppHelper
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import one.yufz.hmspush.XLog

object NotificationManagerEx {
    private const val TAG = "NotificationManagerEx"

    @JvmStatic
    fun getNotificationManager() = this

    private val notificationManager: INotificationManager = createNotificationManager()

    private fun createNotificationManager(): INotificationManager {
        return if (AndroidAppHelper.currentApplication().getSystemService(NotificationManager::class.java).canNotifyAsPackage("android")) {
            XLog.d(TAG, "use SystemNotificationManager")
            SystemNotificationManager()
        } else {
            XLog.d(TAG, "use SelfNotificationManager")
            SelfNotificationManager()
        }
    }

    fun areNotificationsEnabled(packageName: String, userId: Int): Boolean {
        XLog.d(TAG, "areNotificationsEnabled() called with: packageName = $packageName, userId = $userId")
        return notificationManager.areNotificationsEnabled(packageName, userId)
    }

    fun getNotificationChannel(packageName: String, userId: Int, channelId: String, boolean: Boolean): NotificationChannel? {
        XLog.d(TAG, "getNotificationChannel() called with: packageName = $packageName, userId = $userId, channelId = $channelId, boolean = $boolean")
        return notificationManager.getNotificationChannel(packageName, userId, channelId, boolean)
    }

    fun notify(context: Context, packageName: String, id: Int, notification: Notification) {
        XLog.d(TAG, "getNotificationChannel() called with: context = $context, packageName = $packageName, id = $id, notification = $notification")
        notificationManager.notify(context, packageName, id, notification)
    }

    fun createNotificationChannels(packageName: String, userId: Int, channels: List<NotificationChannel>) {
        XLog.d(TAG, "createNotificationChannels() called with: packageName = $packageName, userId = $userId, channels = $channels")
        notificationManager.createNotificationChannels(packageName, userId, channels)
    }

    fun cancelNotification(context: Context, packageName: String, id: Int) {
        XLog.d(TAG, "cancelNotification() called with: context = $context, packageName = $packageName, id = $id")
        notificationManager.cancelNotification(context, packageName, id)
    }

    fun deleteNotificationChannel(packageName: String, channelId: String) {
        XLog.d(TAG, "deleteNotificationChannel() called with: packageName = $packageName, channelId = $channelId")
        notificationManager.deleteNotificationChannel(packageName, channelId)
    }
}