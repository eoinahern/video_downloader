package videodownloader.eoinahern.ie.videodownloader.ui.download

import dagger.Module
import dagger.Subcomponent
import videodownloader.eoinahern.ie.videodownloader.di.components.BaseActivityComponent
import videodownloader.eoinahern.ie.videodownloader.di.modules.BaseActivityModule


@PerScreen
@Subcomponent(modules = arrayOf(DownloadActivityComponent.DownloadActivityModule::class))
public interface DownloadActivityComponent : BaseActivityComponent<DownloadActivity> {

	@Module
	public class DownloadActivityModule(activity: DownloadActivity) : BaseActivityModule<DownloadActivity>(activity) {






	}
}