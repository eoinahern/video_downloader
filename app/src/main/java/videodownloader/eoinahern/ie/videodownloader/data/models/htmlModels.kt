package videodownloader.eoinahern.ie.videodownloader.data.models

import videodownloader.eoinahern.ie.videodownloader.tools.vimeo_url


interface HtmlUtils {
	val suffix : String
	val tag : String
	val attr : String
}

object Vimeo : HtmlUtils  {
	override val suffix =  ".mp4"
	override val tag =  "source"
	override val attr = "src"
}

object Bitchute : HtmlUtils  {
	override val suffix =  ".mp4"
	override val tag =  "source"
	override val attr = "src"
}

object ScreenJunkies : HtmlUtils  {
	override val suffix =  "mp4"
	override val tag =  "source"
	override val attr = "src"
}

object Veoh : HtmlUtils  {
	override val suffix =  "mp4"
	override val tag =  "source"
	override val attr = "src"
}

object DailyMotion : HtmlUtils  {
	override val suffix =  "mp4"
	override val tag =  "source"
	override val attr = "src"
}

object Blip : HtmlUtils  {
	override val suffix =  "mp4"
	override val tag =  "source"
	override val attr = "src"
}

