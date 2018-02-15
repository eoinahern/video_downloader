package videodownloader.eoinahern.ie.videodownloader.di.components


import videodownloader.eoinahern.ie.videodownloader.ui.base.BaseActivity

//read about this covariance!!
 interface  BaseActivityComponent<in T : BaseActivity> {
	fun inject(activity : T)
}