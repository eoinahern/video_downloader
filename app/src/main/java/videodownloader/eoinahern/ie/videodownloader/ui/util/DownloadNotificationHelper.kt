package videodownloader.eoinahern.ie.videodownloader.ui.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.tools.channel_desc
import videodownloader.eoinahern.ie.videodownloader.tools.channel_id

/**
 * Singleton helper class to help manager apps notifications
 * use id to update specific notification. needs to be injected into
 * backgroundDownloadInteractor.
 */

class DownloadNotificationHelper constructor(var context: Context, var notifyManager: NotificationManager) {

	private var notificationID = 1
	private lateinit var builder: Notification.Builder
	private lateinit var builderCompat: NotificationCompat.Builder

	@RequiresApi(Build.VERSION_CODES.O)
	fun createChannel(channelID: String = channel_id) {
		var notificationChannel = notifyManager.getNotificationChannel(channelID)

		if (notificationChannel == null) {

			notificationChannel = NotificationChannel(channelID, channel_desc, NotificationManager.IMPORTANCE_HIGH)
			notificationChannel.lightColor = Color.GREEN
			notificationChannel.enableVibration(true)
			notifyManager.createNotificationChannel(notificationChannel)
		}
	}

	fun createNotification(channelID: String = channel_id): Notification {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			builder = Notification.Builder(context, channelID)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.notification_start))
					.setProgress(100, 0, false)
					.setContentText(context.getString(R.string.notification_txt))

			builder.build()
		} else {
			builderCompat = NotificationCompat.Builder(context, channelID)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.notification_start))
					.setProgress(100, 0, false)
					.setContentText(context.getString(R.string.notification_txt))

			builderCompat.build()
		}

	}

	fun getNotificationID(): Int = notificationID++


	fun updateNotificationProgress(id: Int, amountDone: Long, totalAmount: Long) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

			builder.setProgress(totalAmount.toInt(), amountDone.toInt(), false)
					.setContentText(null)
			notifyManager.notify(id, builder.build())

		} else {

			builderCompat.setProgress(totalAmount.toInt(), amountDone.toInt(), false)
					.setContentText(null)
			notifyManager.notify(id, builderCompat.build())
		}
	}

	fun showNotifcationComplete(id: Int, pendingIntent : PendingIntent) {


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

			builder.setProgress(100, 100, false)
					.setContentTitle(context.getString(R.string.download_complete))
					.setContentIntent(pendingIntent)
			notifyManager.notify(id, builder.build())

		} else {

			builderCompat.setProgress(100, 100, false)
					.setContentTitle(context.getString(R.string.download_complete))
					.setContentIntent(pendingIntent)
			notifyManager.notify(id, builderCompat.build())
		}
	}

	fun showNotificationFailed(id: Int) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

			builder.setContentTitle(context.getString(R.string.download_failed_title))
					.setContentText(context.getString(R.string.download_error))
			notifyManager.notify(id, builder.build())

		} else {

			builder.setContentTitle(context.getString(R.string.download_failed_title))
					.setContentText(context.getString(R.string.download_error))
			notifyManager.notify(id, builder.build())
		}

	}
}
