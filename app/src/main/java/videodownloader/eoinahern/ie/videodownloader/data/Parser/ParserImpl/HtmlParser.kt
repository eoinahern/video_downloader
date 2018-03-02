package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl


import org.jsoup.Jsoup

object HtmlParser : Parser {

	override fun search(pageData: String, tag: String,
						attr: String, suffix: String): String {

		var doc = Jsoup.parse(pageData)
		println(doc.body())
		var sourceElement = doc.body().getElementsByTag(tag)

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
