package videodownloader.eoinahern.ie.videodownloader.interactor.download

import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor


class DownloadInteractor : BaseInteractor<String>() {


	override fun buildObservable(): Observable<String> {
		return Observable.just("hiii")
	}

}
