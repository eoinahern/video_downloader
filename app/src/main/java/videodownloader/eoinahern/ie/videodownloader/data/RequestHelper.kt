package videodownloader.eoinahern.ie.videodownloader.data

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class RequestHelper @Inject constructor() {

	private val youtube: String = "www.youtube.com"
	private val bitchute: String = "www.bitchute.com"
	private val vimeo: String = "www.vimeo.com"
	private val daily_motion: String = "www.dailymotion.com"

	/**
	 * checks if i have a legit URL
	 */

	private fun checkURLState(url: String): HttpUrl? = HttpUrl.parse(url)

	/**
	 * check URL scheme
	 */

	private fun checkURLScheme(httpUrl: HttpUrl): Boolean {

		val scheme = httpUrl.scheme()

		return when (scheme) {
			youtube, vimeo, bitchute, daily_motion -> true
			else -> false
		}
	}

	/**
	 * return the scheme. used to decipher
	 * which parser is required
	 */

	fun getScheme(httpurl: HttpUrl): String {
		return httpurl.scheme()
	}

	/**
	 * return string of html page source
	 */

	fun getPageSource(url: String): String? {

		var httpurl: HttpUrl? = checkURLState(url)


		return httpurl?.let {

			var client = OkHttpClient()
			var req: Request = Request.Builder().url(httpurl).build()

			var resp = client.newCall(req).execute()
			resp.body()?.string()
		}


	}
}
