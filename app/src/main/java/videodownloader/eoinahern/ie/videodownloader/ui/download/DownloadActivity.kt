package videodownloader.eoinahern.ie.videodownloader.ui.download


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotterknife.bindView
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity
import javax.inject.Inject

class DownloadActivity : BaseActivity(), DownloadView {

	private val toolbar: Toolbar by bindView(R.id.toolbar)
	private val downloadBtn: Button by bindView(R.id.download_btn)
	private val urlTxt: EditText by bindView(R.id.url_edtext)


	lateinit @Inject var presenter: DownloadActivityPresenter
	lateinit @Inject var sharedPrefs: SharedPreferences


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_download)
		setUpActionBar()


		sharedPrefs.let {
			Log.d("yay!", "injected!!!")
		}

		presenter.downloadFile("www.hellothere.com")
		//downloadBtn.setOnClickListener { presenter?.downloadFile(urlTxt.text.toString())}
	}

	companion object {
		fun getStartIntent(context: Context): Intent {
			return Intent(context, DownloadActivity::class.java)
		}
	}

	private fun setUpActionBar() {
		toolbar.setTitle(R.string.download_name)
		toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent))
		setSupportActionBar(toolbar)
	}


	override fun activityInject() {
		(applicationContext as MyApp).getComponent().plus(DownloadActivityComponent.DownloadActivityModule(this)).inject(this)
	}


	override fun showLoading() {

	}

	override fun hideLoading() {

	}
}
