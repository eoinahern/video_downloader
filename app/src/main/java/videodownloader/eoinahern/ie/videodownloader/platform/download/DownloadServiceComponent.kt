package videodownloader.eoinahern.ie.videodownloader.platform.download

import dagger.Module
import dagger.Subcomponent
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.di.components.BaseServiceComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.BaseServiceModule
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadService


@PerScreen
@Subcomponent( modules =  arrayOf(DownloadServiceComponent.DownloadServiceModule::class))
interface DownloadServiceComponent  : BaseServiceComponent<DownloadService>{

	@Module
	class DownloadServiceModule(service : DownloadService) : BaseServiceModule<DownloadService>(service)
}
