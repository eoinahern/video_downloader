package videodownloader.eoinahern.ie.videodownloader

import android.app.Application
import android.content.Context
import videodownloader.eoinahern.ie.videodownloader.di.components.AppComponent
import videodownloader.eoinahern.ie.videodownloader.di.components.DaggerAppComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.AppModule

class MyApp : Application() {

	private lateinit var  appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		initAppComponent()
	}

	private fun initAppComponent() {

		appComponent = DaggerAppComponent
				.builder().appModule(AppModule(this)) //deprecated until i use the component to inject somewhere
				.build()
	}

	fun getComponent(): AppComponent {
		return appComponent
	}

	companion object {
		fun get(context: Context) : MyApp = context.applicationContext as MyApp
	}
}

