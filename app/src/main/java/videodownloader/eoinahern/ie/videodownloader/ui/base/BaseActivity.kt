package videodownloader.eoinahern.ie.videodownloader.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val layoutid : Int = getLayout()
		if (layoutid != 0) {
			setContentView(layoutid)
			ButterKnife.bind(this)
		}

		inject()
	}

	abstract fun inject()
	abstract fun getLayout(): Int
}
