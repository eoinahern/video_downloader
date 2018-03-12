package videodownloader.eoinahern.ie.videodownloader.data

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.tools.bitchute_url
import videodownloader.eoinahern.ie.videodownloader.tools.dailymotion_url
import videodownloader.eoinahern.ie.videodownloader.tools.vimeo_url
import javax.inject.Inject

@PerScreen
class RequestHelper @Inject constructor(val client : OkHttpClient) {

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
			vimeo_url, bitchute_url, dailymotion_url -> true
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

			val req: Request = Request.Builder().url(httpurl).build()

			val resp = client.newCall(req).execute()
			resp.body()?.string()
		}
	}
}
