package videodownloader.eoinahern.ie.videodownloader.ui.intro

import android.content.Context
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotterknife.bindView
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity
import videodownloader.eoinahern.ie.videodownloader.ui.download.DownloadActivity

class IntroActivity : BaseActivity(), IntroView {

	private val imView: ImageView by bindView(R.id.imview)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)
		startAnimation(this)
	}

	override fun inject() {
		//not used here
	}

	private fun startAnimation(context: Context) {

		val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.appear)

		anim.setAnimationListener(object : Animation.AnimationListener {
			override fun onAnimationRepeat(p0: Animation?) {}
			override fun onAnimationStart(p0: Animation?) {}

			override fun onAnimationEnd(p0: Animation?) {
				startActivity(DownloadActivity.getStartIntent(context))
				finish()
			}
		})

		imView.startAnimation(anim)
	}
}
