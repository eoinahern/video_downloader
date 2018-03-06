package videodownloader.eoinahern.ie.videodownloader.interactor.filelocation

import io.reactivex.Observable
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.HtmlParser
import videodownloader.eoinahern.ie.videodownloader.data.RequestHelper
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor

import javax.inject.Inject

@PerScreen
class GetFileLoactionInteractor @Inject constructor(private val reqHelper: RequestHelper) : BaseInteractor<String>() {

	lateinit var url: String

	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable {

			val htmlStr : String? = reqHelper.getPageSource(url)
			HtmlParser.search(htmlStr)
		}
	}


}
