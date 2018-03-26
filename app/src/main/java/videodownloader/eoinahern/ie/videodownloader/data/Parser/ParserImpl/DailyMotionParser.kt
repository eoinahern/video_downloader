package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import org.jsoup.Jsoup
import videodownloader.eoinahern.ie.videodownloader.data.Parser.Parser


object DailyMotionParser : Parser {

	private val urlRegex: Regex by lazy { """http:\\/\\/[a-zA-Z.\\/0-9-]*[a-zA-Z\\/]*.mp4(\?auth=[0-9a-zA-z-]*)""".toRegex() }

	override fun search(pageData: String?, tag: String, attr: String, suffix: String): String {
		val doc = Jsoup.parse(pageData)
		println(doc.body())

		val tagElements = doc.body().getElementsByTag(tag)

		for (tagElement in tagElements) {
			if (tagElement.attr(attr) == "page-data") {
				val url = stripUrl(tagElement.data())

				if (url !== null) {
					return url
				}
			}
		}

		return ""
	}

	private fun stripUrl(scriptStr: String): String? {

		val found = urlRegex.find(scriptStr)
		return found?.value?.replace("\\", "")
	}
}
