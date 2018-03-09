package videodownloader.eoinahern.ie.videodownloader.interactor.backgrounddownload

import android.app.NotificationManager
import android.content.Context
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.BufferedSink
import okio.BufferedSource
import okio.Okio
import okio.Okio.sink
import okio.Okio.source
import videodownloader.eoinahern.ie.videodownloader.data.FileHelper
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import java.io.File
import java.nio.channels.Pipe
import javax.inject.Inject


class BackgroundDownloadInteractor @Inject constructor(val client: OkHttpClient,
													   val fileHelper: FileHelper,
													   val notifManager: NotificationManager,
													   val context : Context) : BaseInteractor<Boolean>() {

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

			//not following

			val file = File(context.filesDir, "file")
			var sink : BufferedSink = Okio.buffer(sink(file))

			//set up response Interceptor


			val resp1 = client.newCall(createRequest()).execute()
			var source : BufferedSource = Okio.buffer(resp1.body()?.source())


			if(!fileHelper.checkEnoughSpace())
				println("boo")

			var resp = client.newCall(createRequest()).execute()
			sink.writeAll(resp.body()?.source())


			//could write to
			val strean = resp.body()?.byteStream()
			file.writeBytes(source.readByteArray())



			true
		}
	}

	private fun createRequest(): Request = Request.Builder().url(fileLocation).build()

}
