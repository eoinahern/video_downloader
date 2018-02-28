package videodownloader.eoinahern.ie.videodownloader.data.Parser

import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.VimeoParser


object ParserFactory  {

	/**
	 * depending on page source return specific parser.
	 * presumption. different tube site will need a specific parser
	 */

	fun getParser(pageSource : String)  : Parser{

		return VimeoParser
	}




}
