package videodownloader.eoinahern.ie.videodownloader.platform.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload.BackgroundDownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadService
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import java.io.File
import javax.inject.Inject

class DownloadController @Inject constructor(val backgroundDownloadInteractor: BackgroundDownloadInteractor,
											 val notificationHelper: DownloadNotificationHelper) {

	private lateinit var service: DownloadService

	fun downloadFile(location: String, notificationID: Int) {

		backgroundDownloadInteractor.init(location, notificationID).execute(object : BaseSubscriber<File>() {

			override fun onNext(file: File) {
				Log.d("download", "complete!")

				notificationHelper.showNotifcationComplete(notificationID, service.createPlayVideoIntent(file))
			}

			override fun onError(e: Throwable) {
				Log.d("download", "failed")
				notificationHelper.showNotificationFailed(notificationID)
			}
		})
	}

	fun setService(service: DownloadService) {
		this.service = service
	}

}