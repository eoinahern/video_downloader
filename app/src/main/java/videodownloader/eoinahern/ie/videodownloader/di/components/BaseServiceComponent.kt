package videodownloader.eoinahern.ie.videodownloader.di.components

import android.app.Service


interface BaseServiceComponent<in T : Service> {
	 fun inject(service : T)
}