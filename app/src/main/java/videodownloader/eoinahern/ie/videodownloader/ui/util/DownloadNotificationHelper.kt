package videodownloader.eoinahern.ie.videodownloader.ui.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
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
			Notification.Builder(context, channelID)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.notification_title))
					.setProgress(100, 0, false)
					.setContentText(context.getString(R.string.notification_txt)).build()
		} else {
			NotificationCompat.Builder(context, channelID)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.notification_title))
					.setProgress(100, 0, false)
					.setContentText(context.getString(R.string.notification_txt)).build()
		}

	}

	fun getNotificationID(): Int = notificationID++


	fun updateNotificationImage(id: String) {

	}

	fun updateNotificationProgress(id: Int, amountDone: Int, totalAmount: Int) {
		var notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			Notification.Builder(context, channel_id)
					.setProgress(totalAmount, amountDone, false).build()

		} else {
			NotificationCompat.Builder(context, channel_id)
					.setProgress(totalAmount, amountDone, false).build()
		}

		notifyManager.notify(id, notification)

	}

	fun showNotifcationComplete(id: String) {

	}


	fun showNotificationFailed(id: Int) {

		Log.d("noifid in failed", id.toString())

		var notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			Notification.Builder(context, channel_id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.download_failed_title))
					.setContentText(context.getString(R.string.download_error))
					.build()
		} else {
			NotificationCompat.Builder(context, channel_id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(context.getString(R.string.download_failed_title))
					.setContentText(context.getString(R.string.download_error))
					.build()

		}

		notifyManager.notify(id, notification)
	}
}
