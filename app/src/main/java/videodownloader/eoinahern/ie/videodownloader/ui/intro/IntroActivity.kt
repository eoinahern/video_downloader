package videodownloader.eoinahern.ie.videodownloader.ui.intro

import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity
import videodownloader.eoinahern.ie.videodownloader.ui.download.DownloadActivity
import java.util.concurrent.TimeUnit

class IntroActivity : BaseActivity(), IntroView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)

		Observable.timer(800, TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe { _ -> startActivity(DownloadActivity.Companion.getStartIntent(this)) }
	}

	override fun inject() {
		//not used here
	}

}
