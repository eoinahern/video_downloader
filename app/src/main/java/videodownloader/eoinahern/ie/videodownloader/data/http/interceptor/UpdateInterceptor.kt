package videodownloader.eoinahern.ie.videodownloader.data.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import videodownloader.eoinahern.ie.videodownloader.data.http.request.DownloadUpdateResponseBody

class UpdateInterceptor constructor(var downloadResponseBody: DownloadUpdateResponseBody) : Interceptor {

	override fun intercept(chain: Interceptor.Chain?): Response? {
		var originalResp = chain?.proceed(chain.request())

		//NPE : possible here.
		downloadResponseBody.setRespBody(originalResp?.body()!!)
		return originalResp.newBuilder()?.body(downloadResponseBody)?.build()
	}
}
