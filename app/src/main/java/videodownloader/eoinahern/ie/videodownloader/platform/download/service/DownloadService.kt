package videodownloader.eoinahern.ie.videodownloader.platform.download.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadController
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadServiceComponent
import javax.inject.Inject


class DownloadService : Service() {

	private var notifid: Int = 1
	@Inject
	lateinit var notifManager: NotificationManager
	@Inject
	lateinit var downloadController: DownloadController

	private val id = "boo"
	private val name = "channel"
	private val desc = "download_channel"
	private val importance = NotificationManager.IMPORTANCE_HIGH

	override fun onBind(p0: Intent?): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		(applicationContext as MyApp).getComponent().plus(DownloadServiceComponent.DownloadServiceModule(this)).inject(this)
	}


	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d("service started", "service started")

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			createChannel()
		}

		var notif = createNotification()
		downloadController.downloadFile()
		startForeground(notifid++, notif)

		//stopSelf()
		return Service.START_STICKY
	}


	private fun createNotification(): Notification {

		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			Notification.Builder(this, id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(getString(R.string.notification_title))
					.setProgress(100,0, false)
					.setContentText(getString(R.string.notification_txt)).build()
		} else {
			NotificationCompat.Builder(this, id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(getString(R.string.notification_title))
					.setProgress(100,0, false)
					.setContentText(getString(R.string.notification_txt)).build()
		}
	}

	@RequiresApi(Build.VERSION_CODES.O)
	private fun createChannel() {

		var notificationChannel = notifManager.getNotificationChannel(id)

		if (notificationChannel == null) {

			notificationChannel = NotificationChannel(id, desc, importance)
			notificationChannel.lightColor = Color.GREEN
			notificationChannel.enableVibration(true)
			notifManager.createNotificationChannel(notificationChannel)
		}
	}
}
