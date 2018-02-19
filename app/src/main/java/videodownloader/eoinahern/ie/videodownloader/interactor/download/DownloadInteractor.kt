package videodownloader.eoinahern.ie.videodownloader.interactor.download

import android.content.SharedPreferences
import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor

import javax.inject.Inject

@PerScreen
class DownloadInteractor @Inject constructor(private var sharedPrefs: SharedPreferences) : BaseInteractor<String>() {


	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable {
			"hello theressssss"
		}
	}

}
