package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSink
import okio.BufferedSource
import okio.Okio
import videodownloader.eoinahern.ie.videodownloader.data.FileHelper
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor(val client: OkHttpClient,
													   val fileHelper: FileHelper,
													   val notifManager: NotificationManager,
													   val context: Context) : BaseInteractor<Boolean>() {

	lateinit var fileLocation: String

	fun init(location: String): BackgroundDownloadInteractor {
		fileLocation = location
		return this
	}

	/**
	 * observable that downloads file
	 * and updated Notification as it does so
	 **/
	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {

			var bufferedSink: BufferedSink? = null
			var buffSource: BufferedSource? = null

			try {
				var file = fileHelper.createFile(fileLocation)
				bufferedSink = Okio.buffer(Okio.sink(file))
				var buffer = bufferedSink.buffer()
				var totalBytesRead: Long = 0

				val resp = client.newCall(createRequest()).execute()
				buffSource = resp.body()?.source()


				if (buffSource == null) {
					Log.d("source", "no buff source on resp")
				}

				while (buffSource?.exhausted() != false) {
					var bytesread = buffSource?.read(buffer, 1000)

					bufferedSink.emit()

					bytesread?.let {
						totalBytesRead += it
					}

					//update my progress!!!

					true
				}

			} catch (exc: Exception) {
				exc.printStackTrace()
				false
			} finally {
				bufferedSink?.close()
				buffSource?.close()
			}

			true
		}
	}

	private fun createRequest(): Request = Request.Builder().url(fileLocation).build()

}
