package videodownloader.eoinahern.ie.videodownloader.di.modules


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import videodownloader.eoinahern.ie.videodownloader.MyApp
import javax.inject.Singleton

@Module
class AppModule(var myApp : MyApp) {

	@Singleton
	@Provides
	fun getContext() : Context  =  myApp


	@Provides
	@Singleton
	fun getSharedPrefs(context : Context) : SharedPreferences {
		return PreferenceManager.getDefaultSharedPreferences(context)
	}

}
