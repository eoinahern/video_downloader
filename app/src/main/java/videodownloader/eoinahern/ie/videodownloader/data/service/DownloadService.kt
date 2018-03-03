package videodownloader.eoinahern.ie.videodownloader.data.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.R


class DownloadService : Service() {

	private var notifid : Int = 0
	private lateinit var notifManager : NotificationManager

	override fun onBind(p0: Intent?): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		//setup

		notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
	}


	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d("service started", "service started")

		var notif = NotificationCompat.Builder(this, "boo")
				.setSmallIcon(R.drawable.ic_download_dark)
				.setContentTitle(getString(R.string.notification_title))
				.setContentText(getString(R.string.notification_txt))

		notifManager.notify(notifid++, notif.build())

		stopSelf()
		return Service.START_STICKY
	}


}
