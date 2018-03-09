package videodownloader.eoinahern.ie.videodownloader.data.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import videodownloader.eoinahern.ie.videodownloader.data.http.request.DownloadUpdateResponseBody
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class UpdateInterceptor @Inject constructor(var downloadResponseBody: DownloadUpdateResponseBody) : Interceptor {

	override fun intercept(chain: Interceptor.Chain?): Response? {
		var originalResp = chain?.proceed(chain.request())
		return originalResp?.newBuilder()?.body(downloadResponseBody)?.build()
	}
}
