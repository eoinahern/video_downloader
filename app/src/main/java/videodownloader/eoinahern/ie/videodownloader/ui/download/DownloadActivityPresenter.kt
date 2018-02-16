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
		Log.d("hip", url)

		downloadInteractor.execute(object : BaseSubscriber<String> () {

			override fun onNext(t: String) {
				getView()?.hideLoading()
				//update downloaded info
			}

			override fun onError(t : Throwable?)  {
				super.onError(t)
				getView()?.hideLoading()
				//show some error output

			}


		})
	}
}