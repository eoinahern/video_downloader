package videodownloader.eoinahern.ie.videodownloader.di.modules


import dagger.Module
import dagger.Provides
import videodownloader.eoinahern.ie.videodownloader.MyApp
import javax.inject.Singleton

@Singleton
@Module
class AppModule(var myApp : MyApp) {

	@Singleton
	@Provides
	fun getContext()  = myApp

}
