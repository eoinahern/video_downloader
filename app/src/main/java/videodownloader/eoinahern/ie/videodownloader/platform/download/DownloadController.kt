package videodownloader.eoinahern.ie.videodownloader.platform.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload.BackgroundDownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadService
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import javax.inject.Inject

class DownloadController @Inject constructor(val backgroundDownloadInteractor: BackgroundDownloadInteractor,
											 val notificationHelper: DownloadNotificationHelper) {

	private lateinit var service: DownloadService

	fun downloadFile(location: String, notificationID: Int) {

		backgroundDownloadInteractor.init(location, notificationID).execute(object : BaseSubscriber<Unit>() {

			override fun onNext(t: Unit) {
				Log.d("download", "complete!")
				notificationHelper.showNotifcationComplete(notificationID)
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


	/*fun serviceStop() {

		backgroundDownloadInteractor.dispose()
		Log.d("serviceStop", "service stopped!!")

		service.let {
			it.serviceStop()
		}
	}*/
}