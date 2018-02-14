package videodownloader.eoinahern.ie.videodownloader.di.components

import dagger.Component
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity

@Component
public interface  BaseActivityComponent<in T : BaseActivity> {
	fun Inject(activity : T)
}