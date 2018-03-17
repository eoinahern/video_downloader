package videodownloader.eoinahern.ie.videodownloader.data

import android.content.Context
import android.webkit.URLUtil
import java.io.File
import javax.inject.Inject


class FileHelper @Inject constructor(var cont: Context, var downloadDir: File) {

	private lateinit var filePath: String
	private lateinit var videoFile: File

	fun checkEnoughSpace(fileSize: Int): Boolean {
		return fileSize <= downloadDir.freeSpace
	}

	fun createFile(name: String): File {
		filePath = getBaseLoaction() + name
		videoFile = File(getBaseLoaction(), name)
		videoFile.setWritable(true)
		videoFile.setReadable(true)
		return videoFile
	}

	fun getFilename(urlLocation : String, disposition: String?, mimeType: String?) :String {
		return  URLUtil.guessFileName(urlLocation,  disposition, mimeType)
	}

	private fun getBaseLoaction(): String = downloadDir.path

}