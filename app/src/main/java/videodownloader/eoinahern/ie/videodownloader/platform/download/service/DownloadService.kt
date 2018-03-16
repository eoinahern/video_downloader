package videodownloader.eoinahern.ie.videodownloader.platform.download.service

import android.app.PendingIntent
import java.io.File

interface DownloadService {

	fun serviceStop()
	fun createPlayVideoIntent(file : File) : PendingIntent
}