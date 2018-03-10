package videodownloader.eoinahern.ie.videodownloader.ui.util

import android.app.NotificationChannel
import android.app.NotificationManager
import videodownloader.eoinahern.ie.videodownloader.tools.channel_id

/**
 * Singleton helper class to help manager apps notifications
 * use id to update specific notification
 */

class DownloadNotificationHelper constructor(var notificationManager : NotificationManager){



	public fun createChannel(id :String, channelid : String = channel_id) : NotificationChannel {

	}


	public fun updateNotificationImage(id : String) {

	}

	fun updateNotificationProgress(id : String) {

	}

	fun showNotifcationComplete(id : String) {

	}
}
