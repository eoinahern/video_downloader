package videodownloader.eoinahern.ie.videodownloader.interactor.download

import android.content.SharedPreferences
import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.data.RequestHelper
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor

import javax.inject.Inject

@PerScreen
class DownloadInteractor @Inject constructor(private var sharedPrefs: SharedPreferences,
											 private val reqHelper: RequestHelper) : BaseInteractor<String>() {

	lateinit var url: String


	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable {
			reqHelper.getPageSource(url)
		}
	}


}
