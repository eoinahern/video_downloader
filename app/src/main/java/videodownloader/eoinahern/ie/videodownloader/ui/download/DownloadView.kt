package videodownloader.eoinahern.ie.videodownloader.ui.download

import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseView

interface DownloadView : BaseView {

	fun showError()
	fun showStarted()
}