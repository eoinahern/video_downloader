package videodownloader.eoinahern.ie.videodownloader.platform.download

import videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload.BackgroundDownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import javax.inject.Inject

class DownloadController @Inject constructor(val backgroundDownloadInteractor : BackgroundDownloadInteractor) {

	fun downloadFile() {

		backgroundDownloadInteractor.execute(object : BaseSubscriber<Boolean>() {

			override fun onNext(t: Boolean) {
			}


		})
	}




}