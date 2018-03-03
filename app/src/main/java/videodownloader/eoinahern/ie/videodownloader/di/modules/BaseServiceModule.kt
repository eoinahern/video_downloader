package videodownloader.eoinahern.ie.videodownloader.di.modules

import android.app.Service
import dagger.Module
import dagger.Provides
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen

/**
 * using this to inject into my Service class
 * as it is similar to an Activity but no UI
 * also doesnt extend baseActiivty
 *
 */
@Module
abstract class BaseServiceModule<out T : Service>(private var service: T) {

	@Provides
	@PerScreen
	fun getServiceT(): T {
		return service
	}

	@Provides
	@PerScreen
	fun getService(): Service {
		return service
	}
}
