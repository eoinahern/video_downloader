package videodownloader.eoinahern.ie.videodownloader.ui.download

import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseView

/**
 * Created by eoin_a on 10/12/2017.
 */

interface DownloadView : BaseView {

	fun showLoading()
	fun hideLoading()

}