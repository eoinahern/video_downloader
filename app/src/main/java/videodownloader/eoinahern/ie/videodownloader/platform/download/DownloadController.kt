package videodownloader.eoinahern.ie.videodownloader.platform.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload.BackgroundDownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadService
import javax.inject.Inject

class DownloadController @Inject constructor(val backgroundDownloadInteractor: BackgroundDownloadInteractor) {

	private lateinit var service: DownloadService

	fun downloadFile(location: String?) {

		backgroundDownloadInteractor.init(location).execute(object : BaseSubscriber<Boolean>() {

			override fun onNext(t: Boolean) {
				serviceStop()
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()
				service.updateNotification()
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