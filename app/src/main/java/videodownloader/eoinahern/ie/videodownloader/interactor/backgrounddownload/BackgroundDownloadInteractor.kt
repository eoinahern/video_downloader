package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload


import android.content.Context
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
													   val downloadNotificationHelper: DownloadNotificationHelper,
													   val context: Context) : BaseInteractor<Unit>() {

	private lateinit var fileLocation: String
	private var notificationID: Int = -1

	fun init(location: String, notificationID: Int): BackgroundDownloadInteractor {
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

	//TODO: Cleanup
	override fun buildObservable(): Observable<Unit> = Observable.fromCallable {

		val bufferedSink: BufferedSink?
		val buffSource: BufferedSource?
		val duration = 3000L
		var startTime = System.currentTimeMillis()
		var totalBytesRead: Long = 0

		val resp = client.newCall(createRequest()).execute()
		buffSource = resp.body()?.source()

		val filename = fileHelper.getFilename(fileLocation, resp.headers().get("Content-Disposition"),
				resp.headers().get("Mime-Type"))

		val file = fileHelper.createFile(filename)
		bufferedSink = Okio.buffer(Okio.sink(file))
		val buffer = bufferedSink.buffer()

		val totalAmount = resp.body()?.contentLength() ?: 5000L

		while (buffSource?.exhausted() != true) {
			var bytesRead = buffSource?.read(buffer, 10000)
			bufferedSink?.emit()

			bytesRead?.let {
				totalBytesRead += it
			}

			//only update my notification progressbar every 3 seconds
			if (startTime + duration < System.currentTimeMillis()) {
				downloadNotificationHelper.updateNotificationProgress(notificationID, totalBytesRead, totalAmount)
				startTime = System.currentTimeMillis()
			}
		}

		downloadNotificationHelper.updateNotificationProgress(notificationID, totalBytesRead, totalAmount)

		bufferedSink?.close()
		buffSource.close()
	}

	private fun createRequest(): Request = Request.Builder().url(fileLocation).build()
}
