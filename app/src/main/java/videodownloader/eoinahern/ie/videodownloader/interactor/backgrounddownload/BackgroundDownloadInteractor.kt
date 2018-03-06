package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor() : BaseInteractor<Boolean>() {

	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {  true  }
	}
}
