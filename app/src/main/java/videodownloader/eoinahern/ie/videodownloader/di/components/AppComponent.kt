package videodownloader.eoinahern.ie.videodownloader.di.components

import dagger.Component
import videodownloader.eoinahern.ie.videodownloader.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {


}
