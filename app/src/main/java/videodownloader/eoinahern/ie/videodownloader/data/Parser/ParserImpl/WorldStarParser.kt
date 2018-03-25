package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import org.jsoup.Jsoup
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser


object WorldStarParser : Parser {

	override fun search(pageData: String?, tag: String, attr: String, suffix: String): String {

		val bodyElement = Jsoup.parse(pageData).body()
		val vidElements = bodyElement.select(tag)
		val srcElements = vidElements.select("source")

		for (element in srcElements)  {

			val url = element.attr(attr)

			if (url != null  && url.contains(suffix)) {
				return url.toString()
			}
		}

		return ""
	}

}