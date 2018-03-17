package videodownloader.eoinahern.ie.videodownloader.data.models

import videodownloader.eoinahern.ie.videodownloader.tools.*
import java.io.IOException
import javax.inject.Inject


class HTMLUtilsFactory @Inject constructor() {

	private val bitchuteMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}

	private val dailyMotionMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to "script",
				attr_title to "id")
	}

	private val VeohMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}


	fun getFileParserUtils(scheme: String?): Map<String, String> {

		return when(scheme) {
			dailymotion_url ->  dailyMotionMap
			bitchute_url ->  bitchuteMap
			liveleak_url -> bitchuteMap
			worldstar_url -> bitchuteMap
			else -> throw IOException("unknown url")
		}
	}
}
