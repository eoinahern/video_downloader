package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import org.jsoup.Jsoup
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser

object HtmlParser : Parser {

	override fun search(pageData: String?, tag: String,
						attr: String, suffix: String): String {

		val doc = Jsoup.parse(pageData)
		println(doc.body())
		val sourceElement = doc.body().getElementsByTag(tag)

		for (item in sourceElement) {
			var link = item.attr(attr)

			if (link.endsWith(suffix)) {
				println(link)
				return link
			}
		}

		return ""
	}
}
