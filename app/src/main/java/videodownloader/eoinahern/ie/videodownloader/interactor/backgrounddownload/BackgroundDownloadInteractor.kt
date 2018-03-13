package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload


import android.content.Context
import android.os.Looper
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
													   val downloadNotificationHelper: DownloadNotificationHelper,
													   val context: Context) : BaseInteractor<Boolean>() {

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
	override fun buildObservable(): Observable<Boolean> = Observable.fromCallable {

		var bufferedSink: BufferedSink?
		var buffSource: BufferedSource?

		var file = fileHelper.createFile("hello.mp4")
		bufferedSink = Okio.buffer(Okio.sink(file))


		var buffer = bufferedSink.buffer()
		var totalBytesRead: Long = 0

		val resp = client.newCall(createRequest()).execute()

		buffSource = resp.body()?.source()

		//Log.d("exhausted", buffSource?.exhausted().toString())
		//Log.d("content-length", resp.body()?.contentLength().toString())
		//Log.d("on main", (Looper.myLooper() === Looper.getMainLooper()).toString())

		var totalAmount =  resp.body()?.contentLength() ?: 5000L

		while (buffSource?.exhausted() != true) {
			var bytesRead = buffSource?.read(buffer, 10000)

			bufferedSink?.emit()

			bytesRead?.let {
				totalBytesRead += it
			}

			//downloadNotificationHelper.updateNotificationProgress(notificationID, totalBytesRead, totalAmount)
		}

		downloadNotificationHelper.updateNotificationProgress(notificationID, totalBytesRead, totalAmount)

		bufferedSink?.close()
		buffSource.close()

		true
	}

	private fun createRequest(): Request = Request.Builder().url(fileLocation).build()
}
