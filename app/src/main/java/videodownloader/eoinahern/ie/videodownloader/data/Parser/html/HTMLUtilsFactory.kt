package videodownloader.eoinahern.ie.videodownloader.data.Parser.html

import videodownloader.eoinahern.ie.videodownloader.tools.*
import java.io.IOException
import javax.inject.Inject

/**
 * provides a map with strings used in jsoup to parse tags
 * on a specific site
 */
class HTMLUtilsFactory @Inject constructor() {

	private val bitchuteMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}

	private val dailyMotionMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to "script",
				attr_title to "id")
	}

	private val veohMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}

	private val liveLeakMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to video_tag,
				attr_title to src_attr)
	}

	private val worldStarMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to video_tag,
				attr_title to src_attr)
	}


	fun getFileParserUtils(scheme: String?): Map<String, String> {

		return when (scheme) {
			dailymotion_url -> dailyMotionMap
			bitchute_url -> bitchuteMap
			liveleak_url -> liveLeakMap
			worldstar_url, worldstar_m_url -> worldStarMap
			else -> throw IOException(UNKNOWN_URL)
		}
	}
}
