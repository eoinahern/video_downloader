package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import android.util.Log
import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor() : BaseInteractor<Boolean>() {

	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {
			Log.d("fired", "interactor fired!")
			true  }
	}
}
