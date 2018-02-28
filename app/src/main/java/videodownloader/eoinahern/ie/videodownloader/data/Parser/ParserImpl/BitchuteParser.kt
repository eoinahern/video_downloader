package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl


import org.jsoup.Jsoup
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser

object BitchuteParser : Parser {

	private val tag: String = "source"

	override fun search(pageData: String): String {

		var doc = Jsoup.parse(pageData)
		println(doc.body())
		var sourceElement = doc.body().getElementsByTag(tag)

		for (item in sourceElement) {
			var link = item.attr("src")

			if (link.endsWith(".mp4")) {
				println(link)
				return link
			}
		}

		return ""
	}

}
