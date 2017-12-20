package videodownloader.eoinahern.ie.videodownloader.ui.download


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import kotterknife.bindView
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity

class DownloadActivity : BaseActivity(), DownloadView {

	private val toolbar: Toolbar by bindView(R.id.toolbar)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_download)

		toolbar.setTitle(R.string.intro_name)
		toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent))
		setSupportActionBar(toolbar)
	}

	companion object {
		fun getStartIntent(context: Context): Intent {
			return Intent(context, DownloadActivity::class.java)
		}
	}


	override fun inject() {

	}


	override fun showLoading() {

	}

	override fun hideLoading() {

	}
}
