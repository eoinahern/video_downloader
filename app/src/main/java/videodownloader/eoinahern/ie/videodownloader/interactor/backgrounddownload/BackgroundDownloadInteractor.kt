package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import io.reactivex.Observable
import okhttp3.OkHttpClient
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor(val client : OkHttpClient) : BaseInteractor<Boolean>() {


	lateinit var  fileLocation : String

	/**
	 * observable that downloads file
	 * and updated Notification as it does so
	 **/
	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {

			//check enough space else fail.
			//download file from location
			//create file on device with video name
			//save file
			//return true



			true
		}
	}
}
