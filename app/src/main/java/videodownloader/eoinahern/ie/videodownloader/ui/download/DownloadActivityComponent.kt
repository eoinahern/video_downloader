package videodownloader.eoinahern.ie.videodownloader.ui.download

import dagger.Module
import dagger.Subcomponent
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.di.components.BaseActivityComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.BaseActivityModule


@PerScreen
@Subcomponent(modules = arrayOf(DownloadActivityComponent.DownloadActivityModule::class))
interface DownloadActivityComponent : BaseActivityComponent<DownloadActivity> {

	@Module
	class DownloadActivityModule(activity: DownloadActivity) : BaseActivityModule<DownloadActivity>(activity)
}