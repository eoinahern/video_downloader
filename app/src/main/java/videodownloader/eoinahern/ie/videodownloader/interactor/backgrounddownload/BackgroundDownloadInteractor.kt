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
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor(val client: OkHttpClient,
													   val fileHelper: FileHelper,
													   val downloadNotificationHelper : DownloadNotificationHelper,
													   val context: Context) : BaseInteractor<Boolean>() {

	private lateinit var fileLocation: String
	private lateinit var notificationID : String

	fun init(location: String, notificationID: String): BackgroundDownloadInteractor {
		fileLocation = location
		this.notificationID = notificationID
		return this
	}

	/**
	 * observable that downloads file
	 * and updated Notification as it does so
	 * may need to pass notification id, channelID etc.
	 *  There may be multiple observables executing at the same time
	 * to download multiple files
	 **/
	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {

			Log.d("noifid", notificationID)

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
					downloadNotificationHelper.updateNotificationProgress(notificationID)

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
