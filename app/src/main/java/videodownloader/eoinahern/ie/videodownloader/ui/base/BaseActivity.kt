package videodownloader.eoinahern.ie.videodownloader.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activityInject()
	}

	abstract fun activityInject()
}
