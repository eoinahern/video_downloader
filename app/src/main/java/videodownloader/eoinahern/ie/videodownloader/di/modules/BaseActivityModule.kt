package videodownloader.eoinahern.ie.videodownloader.di.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity


@Module
 abstract class BaseActivityModule<out T : BaseActivity>(private var activity: T) {

	@Provides
	@PerScreen
	fun activityT() : T = activity


	@Provides
	@PerScreen
	fun activity() : Activity = activity
}