package videodownloader.eoinahern.ie.videodownloader.data.Parser

import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.BitchuteParser
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl.VimeoParser
import videodownloader.eoinahern.ie.videodownloader.di.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class ParserFactory @Inject constructor()  {

	/**
	 * depending on page source return specific parser.
	 * presumption. different tube site will need a specific parser
	 */

	fun getParser(pageSource : String)  : Parser{
		return BitchuteParser
	}




}
