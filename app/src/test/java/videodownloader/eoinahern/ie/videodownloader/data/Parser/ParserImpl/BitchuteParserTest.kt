package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import de.jodamob.kotlin.testrunner.KotlinTestRunner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(KotlinTestRunner::class)
 class BitchuteParserTest {

	private val url: String = "www.google.com/5yryeywj.mp4"

	/**
	 * test correct url returned
	 */
	@Test
	fun searchTest() {

		var link = BitchuteParser.search(getHtmlString())
		Assert.assertEquals(url, link)
	}

	/**
	 * fake html
	 */
	private fun getHtmlString(): String {
		return "<html><head></head><body><source src=$url></source></body></html>"
	}

}