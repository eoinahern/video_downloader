package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import videodownloader.eoinahern.ie.videodownloader.tools.mp4_suffix
import videodownloader.eoinahern.ie.videodownloader.tools.source_tag
import videodownloader.eoinahern.ie.videodownloader.tools.src_attr


interface Parser {
	fun search(pageData: String?, tag: String = source_tag, attr: String = src_attr,
			   suffix: String = mp4_suffix): String
}