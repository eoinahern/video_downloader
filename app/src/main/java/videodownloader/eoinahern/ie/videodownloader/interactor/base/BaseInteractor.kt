package videodownloader.eoinahern.ie.videodownloader.interactor.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open abstract class BaseInteractor<T> {

	var disposables: CompositeDisposable = CompositeDisposable()

	fun execute(obs: DisposableObserver<T>) {

		disposables.add(buildObservable()
				.subscribeOn(getIOScheduler())
				.observeOn(getMainThread())
				.subscribeWith(obs))
	}

	fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()

	fun getIOScheduler(): Scheduler = Schedulers.io()

	abstract fun buildObservable(): Observable<T>

	protected fun dispose() {
		if (!disposables.isDisposed)
			disposables.dispose()
	}
}
