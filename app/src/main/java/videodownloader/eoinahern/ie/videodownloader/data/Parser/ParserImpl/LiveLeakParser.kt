package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser


object LiveLeakParser : Parser {

	override fun search(pageData: String?, tag: String, attr: String, suffix: String): String {

		val baseDoc = Jsoup.parse(pageData)
		val vidElements: Elements = baseDoc.body().select(tag)
		val srcElements = vidElements.select("source")

		for (item in srcElements) {

			val url = item.attr(attr)

			if (url != null  && url.contains(suffix)) {
				return url.toString()
			}
		}

		return ""
	}

}
