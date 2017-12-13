package videodownloader.eoinahern.ie.videodownloader.ui.base



interface Presenter<V : BaseView> {

	fun getView() : V?
	fun attachView(view : V)
	fun detachView()
}
