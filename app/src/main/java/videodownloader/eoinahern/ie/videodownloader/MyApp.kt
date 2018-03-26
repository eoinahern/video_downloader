package videodownloader.eoinahern.ie.videodownloader

import android.app.Application
import android.content.Context
import videodownloader.eoinahern.ie.videodownloader.di.components.AppComponent
import videodownloader.eoinahern.ie.videodownloader.di.components.DaggerAppComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.AppModule
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



class MyApp : Application() {

	private lateinit var  appComponent: AppComponent


	override fun onCreate() {
		super.onCreate()
		initAppComponent()
		Fabric.with(this, Crashlytics())
	}

	private fun initAppComponent() {

		appComponent = DaggerAppComponent
				.builder().appModule(AppModule(this))
				.build()
	}

	fun getComponent(): AppComponent {
		return appComponent
	}

	companion object {
		fun get(context: Context) : MyApp = context.applicationContext as MyApp
	}
}

