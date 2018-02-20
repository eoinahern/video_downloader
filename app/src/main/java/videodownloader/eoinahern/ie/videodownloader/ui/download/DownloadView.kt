package videodownloader.eoinahern.ie.videodownloader.ui.download

import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseView

interface DownloadView : BaseView {

	fun showLoading()
	fun hideLoading()
	fun showError()
	fun showStarted()
}