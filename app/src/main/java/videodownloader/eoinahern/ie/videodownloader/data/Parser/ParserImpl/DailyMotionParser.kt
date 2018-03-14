package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import org.jsoup.Jsoup


object DailyMotionParser : Parser {

	override fun search(pageData: String?, tag: String, attr: String, suffix: String): String {
		val doc = Jsoup.parse(pageData)
		println(doc.body())

		val tagElements = doc.body().getElementsByTag(tag)

		for(tag in tagElements){
			if(tag.attr(attr) == "page-data") {
				return  tag.data()
			}
		}

		//from here we need to parse a mess of a string!!

		return ""
	}


}