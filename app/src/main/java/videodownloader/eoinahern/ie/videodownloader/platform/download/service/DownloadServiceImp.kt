package videodownloader.eoinahern.ie.videodownloader.platform.download.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadController
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadServiceComponent
import videodownloader.eoinahern.ie.videodownloader.tools.location_intent_title
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import javax.inject.Inject


class DownloadServiceImp : Service(), DownloadService {

	@Inject
	lateinit var notificationHelper: DownloadNotificationHelper
	@Inject
	lateinit var downloadController: DownloadController

	override fun onBind(p0: Intent?): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		(applicationContext as MyApp).getComponent().plus(DownloadServiceComponent.DownloadServiceModule(this)).inject(this)
		downloadController.setService(this)

	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d("service started", "service started")
		var location: String = intent?.getStringExtra(location_intent_title) ?: ""

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			notificationHelper.createChannel()
		}

		var notif = notificationHelper.createNotification()
		var notificationID = notificationHelper.getNotificationID()
		downloadController.downloadFile(location, notificationID)

		startForeground(notificationID, notif)
		return Service.START_STICKY

	}

	override fun serviceStop() {
		stopSelf()

	}
}
