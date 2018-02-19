package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.interactor.download.DownloadInteractor
import videodownloader.eoinahern.ie.videodownloader.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DownloadActivityPresenter @Inject constructor(private var downloadInteractor : DownloadInteractor) : BasePresenter<DownloadView>() {


	fun downloadFile(url : String) {

		downloadInteractor.url = url
		downloadInteractor.execute(object : BaseSubscriber<String> () {

			override fun onNext(t: String) {
				getView()?.hideLoading()
				Log.d("string returned!!", t)
			}

			override fun onError(e: Throwable) {
				super.onError(e)
				getView()?.hideLoading()
			}


		})
	}

	override fun detachView() {
		super.detachView()
		downloadInteractor.dispose()
	}
}