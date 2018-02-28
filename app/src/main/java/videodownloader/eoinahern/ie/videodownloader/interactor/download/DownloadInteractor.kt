package videodownloader.eoinahern.ie.videodownloader.interactor.download

import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserFactory
import videodownloader.eoinahern.ie.videodownloader.data.RequestHelper
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor

import javax.inject.Inject

@PerScreen
class DownloadInteractor @Inject constructor(private val reqHelper: RequestHelper,
											 private val parserFactory : ParserFactory) : BaseInteractor<String>() {

	lateinit var url: String

	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable {

			//1. check url is legit.
			//2. get page source
			//3. select parser.
			//4. find video file url!

			  val parser = parserFactory.getParser(url)
			  val stuff : String? = reqHelper.getPageSource(url)
			 stuff
		}
	}


}
