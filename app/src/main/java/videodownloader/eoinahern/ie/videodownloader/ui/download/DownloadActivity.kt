package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import com.afollestad.materialdialogs.MaterialDialog
import kotterknife.bindView
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.tools.NOTIFID
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity
import javax.inject.Inject


class DownloadActivity : BaseActivity(), DownloadView {

	private val toolbar: Toolbar by bindView(R.id.toolbar)
	private val downloadBtn: Button by bindView(R.id.download_btn)
	private val urlTxt: EditText by bindView(R.id.url_edtext)
	private val constriant: ConstraintLayout by bindView(R.id.constraint)
	private lateinit var snackbar: Snackbar
	@Inject
	lateinit var presenter: DownloadActivityPresenter
	@Inject
	lateinit var notificationManager: NotificationManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_download)
		setUpActionBar()

		presenter.attachView(this)
		downloadBtn.setOnClickListener { presenter.downloadFile(urlTxt.text.toString()) }
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

	/**
	 *  need to show loading notification here.
	 */
	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun showError() {

		MaterialDialog.Builder(this)
				.title(R.string.error)
				.titleColorRes(R.color.colorPrimaryDark)
				.content(R.string.unrecogised_url)
				.negativeText(R.string.cancel)
				.negativeColorRes(R.color.colorPrimaryDark)
				.onNegative { dialog, _ -> dialog.dismiss() }
				.show()

		urlTxt.text.clear()

	}

	/**
	 * build a notification
	 */
	private fun buildNotification(): NotificationCompat.Builder = NotificationCompat.Builder(this, "id")
				.setSmallIcon(R.drawable.ic_download_dark)
				.setContentTitle("fuck")
				.setContentText("fuckoff")


	override fun showStarted() {

		snackbar = Snackbar.make(constriant, R.string.loading_started, Snackbar.LENGTH_LONG)
		snackbar.show()
		urlTxt.text.clear()

		notificationManager.notify(NOTIFID, buildNotification().build())
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
