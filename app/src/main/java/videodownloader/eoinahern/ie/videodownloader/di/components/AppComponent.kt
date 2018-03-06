package videodownloader.eoinahern.ie.videodownloader.di.components

import dagger.Component
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.di.modules.AppModule
import videodownloader.eoinahern.ie.videodownloader.platform.download.DownloadServiceComponent
import videodownloader.eoinahern.ie.videodownloader.ui.download.DownloadActivityComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

	fun plus(module : DownloadActivityComponent.DownloadActivityModule) : DownloadActivityComponent

	fun plus(module : DownloadServiceComponent.DownloadServiceModule) : DownloadServiceComponent
}
