package videodownloader.eoinahern.ie.videodownloader.ui.download

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import com.afollestad.materialdialogs.MaterialDialog
import kotterknife.bindView
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadServiceImp
import videodownloader.eoinahern.ie.videodownloader.tools.PERMISSION_WRITE_STORAGE
import videodownloader.eoinahern.ie.videodownloader.tools.channel_id
import videodownloader.eoinahern.ie.videodownloader.tools.location_intent_title
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
	private var notifyID: Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_download)
		setUpActionBar()
		checkFilePermissions()

		presenter.attachView(this)
	}

	fun checkFilePermissions() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			showSelectPermissions()
		} else {
			downloadBtn.setOnClickListener { presenter.downloadFile(urlTxt.text.toString()) }
		}
	}

	private fun showSelectPermissions() {

		ActivityCompat.requestPermissions(this,
				arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_WRITE_STORAGE)

	}

	override fun onRequestPermissionsResult(requestCode: Int,
											permissions: Array<String>, grantResults: IntArray) {
		when (requestCode) {
			PERMISSION_WRITE_STORAGE -> {
				// If request is cancelled, the result arrays are empty.
				if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

					// permission was granted, yay! Do the
					//can write to file!!!
					downloadBtn.setOnClickListener { presenter.downloadFile(urlTxt.text.toString()) }

				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					//show dialog.
					downloadBtn.setOnClickListener { showSelectPermissions() }
				}
				return
			}
		}
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
	private fun buildNotification(): NotificationCompat.Builder = NotificationCompat.Builder(this, channel_id)
			.setSmallIcon(R.drawable.ic_download_dark)
			.setContentTitle(getString(R.string.notification_title))
			.setContentText(getString(R.string.notification_txt))


	override fun showStarted(location: String) {

		snackbar = Snackbar.make(constriant, R.string.loading_started, Snackbar.LENGTH_LONG)
		snackbar.show()
		urlTxt.text.clear()

		notificationManager.notify(notifyID++, buildNotification().build())
		startDownloadService(location)

	}

	private fun startDownloadService(location: String) {

		var intent = Intent(this, DownloadServiceImp::class.java)
		intent.putExtra(location_intent_title, location)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			startForegroundService(intent)
		} else {
			startService(intent)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
