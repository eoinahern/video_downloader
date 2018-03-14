package videodownloader.eoinahern.ie.videodownloader.data.models

import videodownloader.eoinahern.ie.videodownloader.tools.*
import javax.inject.Inject


class HTMLUtilsFactory @Inject constructor() {

	val bitchuteMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}

	val vimeoMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}

	val VeohMap: Map<String, String> by lazy {
		mapOf(suffix_title to mp4_suffix, tag_title to source_tag,
				attr_title to src_attr)
	}


	fun getFileParserUtils(scheme: String): Map<String, String> {
		return bitchuteMap
	}
}
