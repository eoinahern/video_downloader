package videodownloader.eoinahern.ie.videodownloader.ui.base

open class BasePresenter<V : BaseView>  : Presenter<V> {

	private var view : V ?= null

	override fun getView(): V? = this.view

	override fun attachView(view: V) {
		this.view = view
	}

	override fun detachView() {
		this.view = null
	}
}