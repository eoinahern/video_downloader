package videodownloader.eoinahern.ie.videodownloader.data.http.request

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

/**
 * add notification helper to update and write to file
 */

class DownloadUpdateResponseBody : ResponseBody() {

	private lateinit var responseBody: ResponseBody
	private lateinit var bufferedSource: BufferedSource
	private lateinit var channelid: String


	override fun contentLength(): Long {
		return responseBody.contentLength()
	}

	override fun source(): BufferedSource {

		bufferedSource = Okio.buffer(source(responseBody.source()))
		return bufferedSource
	}

	override fun contentType(): MediaType? {
		return responseBody.contentType()
	}

	private fun source(source: Source): Source {
		return object : ForwardingSource(source) {

			/**
			 * update specific notification
			 */
			override fun read(sink: Buffer?, byteCount: Long): Long {
				super.read(sink, byteCount)
				return 0L
			}
		}
	}

	fun setRespBody(resp: ResponseBody) {
		responseBody = resp
	}

	fun setChannel(id: String) {
		channelid = id
	}
}
