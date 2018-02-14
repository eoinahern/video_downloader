package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.util.Log
import videodownloader.eoinahern.ie.videodownloader.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class DownloadActivityPresenter @Inject constructor() : BasePresenter<DownloadView>() {



	fun downloadFile(url : String) {
		Log.d("hip", url)


		// download file and store it on the device.
		//thanks!!!
	}
}