package videodownloader.eoinahern.ie.videodownloader.data.models

import javax.inject.Inject

class HTMLUtilsFactory @Inject constructor(){

	fun getFileParserUtils(scheme : String) : HtmlUtils {
		return Vimeo
	}
}
