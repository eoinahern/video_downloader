package videodownloader.eoinahern.ie.videodownloader.interactor.filelocation

import io.reactivex.Observable
import okhttp3.HttpUrl
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserFactory
import videodownloader.eoinahern.ie.videodownloader.data.RequestHelper
import videodownloader.eoinahern.ie.videodownloader.data.Parser.html.HTMLUtilsFactory
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import videodownloader.eoinahern.ie.videodownloader.interactor.base.BaseInteractor
import videodownloader.eoinahern.ie.videodownloader.tools.attr_title
import videodownloader.eoinahern.ie.videodownloader.tools.suffix_title
import videodownloader.eoinahern.ie.videodownloader.tools.tag_title

import javax.inject.Inject

@PerScreen
class GetFileLoactionInteractor @Inject constructor(private val reqHelper: RequestHelper,
													private val htmlUtilsFactory: HTMLUtilsFactory,
													private val parserFactory: ParserFactory) : BaseInteractor<String>() {

	lateinit var url: String

	override fun buildObservable(): Observable<String> {

		return Observable.fromCallable {

			val httpurl = HttpUrl.parse(url)
			val host = httpurl?.host()

			val htmlUtils = htmlUtilsFactory.getFileParserUtils(host)
			val parser: Parser = parserFactory.searchParser(host)
			val htmlStr: String? = reqHelper.getPageSource(url)

			parser.search(htmlStr, htmlUtils.getValue(tag_title),
			htmlUtils.getValue(attr_title), htmlUtils.getValue(suffix_title))
		}
	}
}
