package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.interactor.download.DownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DownloadActivityPresenter @Inject constructor(private var downloadInteractor: DownloadInteractor) : BasePresenter<DownloadView>() {


	fun downloadFile(url: String) {

		downloadInteractor.url = url
		downloadInteractor.execute(object : BaseSubscriber<String>() {

			override fun onNext(t: String) {

				if (t.isEmpty()) {
					Log.d("empyyyy", "empty")
					getView()?.showError()
					return
				}

				getView()?.showStarted()
			}

			override fun onError(e: Throwable) {
				super.onError(e)
					getView()?.showError()
			}
		})
	}

	override fun detachView() {
		super.detachView()
		downloadInteractor.dispose()
	}
}