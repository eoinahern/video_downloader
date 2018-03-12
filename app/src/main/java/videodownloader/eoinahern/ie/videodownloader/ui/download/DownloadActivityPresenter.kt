package videodownloader.eoinahern.ie.videodownloader.ui.download

import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseSubscriber
import videodownloader.eoinahern.ie.videodownloader.interactor.filelocation.GetFileLoactionInteractor
import videodownloader.eoinahern.ie.videodownloader.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DownloadActivityPresenter @Inject constructor(private var getFileLoactionInteractor: GetFileLoactionInteractor) : BasePresenter<DownloadView>() {


	fun downloadFile(url: String) {

		getFileLoactionInteractor.url = url
		getFileLoactionInteractor.execute(object : BaseSubscriber<String>() {

			override fun onNext(location: String) {

				if (location.isEmpty()) {
					getView()?.showError()
					return
				}

				getView()?.showStarted(location)
			}

			override fun onError(e: Throwable) {
				super.onError(e)
					getView()?.showError()
			}
		})
	}

	override fun detachView() {
		super.detachView()
		getFileLoactionInteractor.dispose()
	}
}