package videodownloader.eoinahern.ie.videodownloader.data.Parser

import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.DailyMotionParser
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.HtmlParser
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.LiveLeakParser
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.WorldStarParser
import videodownloader.eoinahern.ie.videodownloader.tools.bitchute_url
import videodownloader.eoinahern.ie.videodownloader.tools.dailymotion_url
import videodownloader.eoinahern.ie.videodownloader.tools.liveleak_url
import videodownloader.eoinahern.ie.videodownloader.tools.worldstar_url
import java.io.IOException


object ParserFactory {

	fun searchParser(hostAddress : String?) : Parser {

		return when(hostAddress) {

			dailymotion_url -> DailyMotionParser
			liveleak_url ->  LiveLeakParser
			worldstar_url ->  WorldStarParser
			bitchute_url ->  HtmlParser
			else -> throw IOException("unkown url")
		}
	}
}
