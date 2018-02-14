package videodownloader.eoinahern.ie.videodownloader.di.modules

import dagger.Module
import dagger.Provides
import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity



@Module
open  abstract public class BaseActivityModule<T : BaseActivity>(private var activity: T) {

	@Provides
	public fun get() : T = activity


}