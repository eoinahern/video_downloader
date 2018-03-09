package videodownloader.eoinahern.ie.videodownloader.data

import android.content.Context
import java.io.File
import javax.inject.Inject


class FileHelper @Inject constructor(var cont : Context, var downloadDir : File)  {

	private lateinit var filePath : String
	private lateinit var videoFile : File

	fun checkEnoughSpace(fileSize : Int)  : Boolean {
		return fileSize <= downloadDir.freeSpace
	}

	fun createFile(name: String) : File {
		filePath = getBaseLoaction() + name
		videoFile = File(getBaseLoaction(), name)
		return videoFile
	}

	fun getVideoFile() :  File {
		return videoFile
	}

	private fun  getBaseLoaction() : String = downloadDir.absolutePath


	fun saveFile() {

	}

}