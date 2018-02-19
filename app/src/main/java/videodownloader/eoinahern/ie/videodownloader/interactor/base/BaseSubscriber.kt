package videodownloader.eoinahern.ie.videodownloader.interactor.base

import android.util.Log
import io.reactivex.observers.DisposableObserver

open abstract class BaseSubscriber<T> : DisposableObserver<T>() {

	override fun onComplete() {
		Log.d("complete", "on Complete")
	}

	override fun onError(e: Throwable) = e.printStackTrace()
}
