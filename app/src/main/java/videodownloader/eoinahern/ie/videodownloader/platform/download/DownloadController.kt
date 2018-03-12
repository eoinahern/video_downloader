package videodownloader.eoinahern.ie.videodownloader.platform.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload.BackgroundDownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadService
import javax.inject.Inject

class DownloadController @Inject constructor(val backgroundDownloadInteractor: BackgroundDownloadInteractor) {

	private lateinit var service: DownloadService

	fun downloadFile(location: String, notificationID: Int) {

		backgroundDownloadInteractor.init(location, notificationID).execute(object : BaseSubscriber<Boolean>() {

			override fun onNext(t: Boolean) {
				serviceStop()
			}


			//show download failed notification
			override fun onError(e: Throwable) {
				e.printStackTrace()
				serviceStop()
			}
		})
	}

	fun setService(service: DownloadService) {
		this.service = service
	}


	fun serviceStop() {

		backgroundDownloadInteractor.dispose()
		Log.d("serviceStop", "service stopped!!")

		service.let {
			it.serviceStop()
		}
	}
}