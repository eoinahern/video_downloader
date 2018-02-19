package videodownloader.eoinahern.ie.videodownloader.data

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject


class RequestHelper @Inject constructor() {

	private val youtube: String = "www.youtube.com"
	private val bitchute: String = "www.bitchute.com"
	private val vimeo: String = "www.vimeo.com"

	/**
	 * checks if i have a legit URL
	 */

	private fun checkURLState(url: String): HttpUrl?  = HttpUrl.parse(url)

	/**
	 * check URL scheme
	 */

	private fun checkURLScheme(httpUrl: HttpUrl): Boolean {

		val scheme = httpUrl.scheme()

		return when (scheme) {
			youtube, vimeo, bitchute -> true
			else -> false
		}
	}

	fun getPageSource(url: String): String? {

		var data  = ""
		var httpurl : HttpUrl? = checkURLState(url)

		if (httpurl === null) {
			Log.d("error", "problem with schema!!")
			return data
		}

		var client = OkHttpClient()
		var req : Request = Request.Builder().url(httpurl).build()

		var resp = client.newCall(req).execute()

		return resp.body()?.string()
	}
}
