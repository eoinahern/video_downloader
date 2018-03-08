package videodownloader.eoinahern.ie.videodownloader.platform.download

import dagger.Module
import dagger.Subcomponent
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.di.components.BaseServiceComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.BaseServiceModule
import videodownloader.eoinahern.ie.videodownloader.platform.download.service.DownloadServiceImp


@PerScreen
@Subcomponent( modules =  arrayOf(DownloadServiceComponent.DownloadServiceModule::class))
interface DownloadServiceComponent  : BaseServiceComponent<DownloadServiceImp>{

	@Module
	class DownloadServiceModule(service : DownloadServiceImp) : BaseServiceModule<DownloadServiceImp>(service)
}
