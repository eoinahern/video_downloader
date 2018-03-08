package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import android.app.NotificationManager
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import videodownloader.eoinahern.ie.videodownloader.data.FileHelper
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor(val client: OkHttpClient,
													   val fileHelper: FileHelper,
													   val notifManager: NotificationManager) : BaseInteractor<Boolean>() {


	lateinit var fileLocation: String

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

			/*if(!fileHelper.checkEnoughSpace())
				println("boo")

			var resp = client.newCall(createRequest()).execute()*/





			true
		}
	}

	private fun createRequest(): Request = Request.Builder().url(fileLocation).build()

}
