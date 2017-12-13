package videodownloader.eoinahern.ie.videodownloader.ui.intro

import android.os.Bundle
import videodownloader.eoinahern.ie.videodownloader.R
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity

class IntroActivity : BaseActivity(), IntroView {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)
	}

	override fun inject() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getLayout(): Int {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}
