package videodownloader.eoinahern.ie.videodownloader.platform.download.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadController
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadServiceComponent
import javax.inject.Inject


class DownloadService : Service() {

	private var notifid : Int = 1
	private lateinit var notifManager : NotificationManager
	@Inject lateinit var downloadController : DownloadController

	override fun onBind(p0: Intent?): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		//setup
		(applicationContext as MyApp).getComponent().plus(DownloadServiceComponent.DownloadServiceModule(this)).inject(this)
		notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
	}



	@SuppressLint("NewApi")
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d("service started", "service started")

		val id  = "boo"
		val name = "channel"
		val desc = "download_channel"

		//still not sure what im at with these new notifications and services
		//playing fucking around.

		var notif : Notification

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

			var notificationChannel = notifManager.getNotificationChannel(id)

			if (notificationChannel == null) {

				val importance = NotificationManager.IMPORTANCE_HIGH
				notificationChannel = NotificationChannel(id, desc, importance)
				notificationChannel.lightColor = Color.GREEN
				notificationChannel.enableVibration(true)
				notifManager.createNotificationChannel(notificationChannel)
			}

			 notif = Notification.Builder(this, id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(getString(R.string.notification_title))
					 .setChannelId(id)
					.setContentText(getString(R.string.notification_txt)).build()

		} else {

			 notif = NotificationCompat.Builder(this, id)
					.setSmallIcon(R.drawable.ic_download_dark)
					.setContentTitle(getString(R.string.notification_title))
					.setContentText(getString(R.string.notification_txt)).build()

		}

		downloadController.downloadFile()
		startForeground(notifid++ , notif)

		//stopSelf()
		return Service.START_STICKY
	}


}
