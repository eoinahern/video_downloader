package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DownloadActivityPresenter @Inject constructor() : BasePresenter<DownloadView>() {

	fun downloadFile(url : String) {
		Log.d("hip", url)
	}
}