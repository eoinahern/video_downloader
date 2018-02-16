package videodownloader.eoinahern.ie.videodownloader.interactor.download

import android.content.SharedPreferences
import android.util.Log
import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import java.util.concurrent.Callable
import javax.inject.Inject

@PerScreen
class DownloadInteractor @Inject constructor(private var sharedPrefs: SharedPreferences) : BaseInteractor<String>() {


	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable(object : Callable<String> {
			override fun call(): String {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

		})
	}

}
