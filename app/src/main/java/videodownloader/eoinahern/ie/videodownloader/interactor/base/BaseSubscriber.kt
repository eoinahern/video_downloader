package videodownloader.eoinahern.ie.videodownloader.interactor.base

import android.util.Log
import io.reactivex.subscribers.DisposableSubscriber


open abstract class BaseSubscriber<T> : DisposableSubscriber<T>() {


	override  abstract fun onNext(t: T)


	override fun onComplete() {
		Log.d("complete", "on Complete")
	}


	override fun onError(e: Throwable) {
		e.printStackTrace()
	}


}
