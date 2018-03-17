package videodownloader.eoinahern.ie.videodownloader.platform.download.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadController
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadServiceComponent
import videodownloader.eoinahern.ie.videodownloader.tools.location_intent_title
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import java.io.File
import javax.inject.Inject

class DownloadServiceImp : Service(), DownloadService {

	@Inject
	lateinit var notificationHelper: DownloadNotificationHelper
	@Inject
	lateinit var downloadController: DownloadController

	private var reqCode = 1

	override fun onBind(p0: Intent?): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		(applicationContext as MyApp).getComponent().plus(DownloadServiceComponent.DownloadServiceModule(this)).inject(this)
		downloadController.setService(this)

	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		var location: String = intent?.getStringExtra(location_intent_title) ?: ""

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			notificationHelper.createChannel()
		}

		var notification = notificationHelper.createNotification()
		var notificationID = notificationHelper.getNotificationID()
		downloadController.downloadFile(location, notificationID)

		startForeground(notificationID, notification)
		return Service.START_STICKY

	}

	override fun createPlayVideoIntent(file: File): PendingIntent {

		var intent = Intent(Intent.ACTION_VIEW)
		intent = intent
				.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
				.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

		val uri = Uri.fromFile(file)
		intent.setDataAndType(uri.normalizeScheme(), "video/*")
		return PendingIntent.getActivity(this, reqCode++, intent, PendingIntent.FLAG_UPDATE_CURRENT)
	}

	override fun serviceStop() {
		stopSelf()
	}
}
