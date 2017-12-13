package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.os.Bundle
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity

class DownloadActivity : BaseActivity(), DownloadView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun getLayout(): Int {
		return R.layout.activity_download
	}

	override fun inject() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun showLoading() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun hideLoading() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
