package videodownloader.eoinahern.ie.videodownloader.data.http.request

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.BufferedSink
import okio.BufferedSource
import javax.inject.Inject


/**
 * add notification helper to update and write to file
 *
 */


class DownloadUpdateResponseBody constructor(): ResponseBody() {




	override fun contentLength(): Long {

	}

	override fun source(): BufferedSource {

	}

	override fun contentType(): MediaType? {

	}


}
