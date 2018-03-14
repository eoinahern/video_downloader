package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import de.jodamob.kotlin.testrunner.KotlinTestRunner
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import videodownloader.eoinahern.ie.videodownloader.data.models.HTMLUtilsFactory
import videodownloader.eoinahern.ie.videodownloader.tools.attr_title
import videodownloader.eoinahern.ie.videodownloader.tools.dailymotion_url
import videodownloader.eoinahern.ie.videodownloader.tools.suffix_title
import videodownloader.eoinahern.ie.videodownloader.tools.tag_title


@RunWith(KotlinTestRunner::class)
class DailyMotionParserTest {

	private val testAnswer = "yay"

	@Test
	fun searchTest() {

		val utils = HTMLUtilsFactory().getFileParserUtils(dailymotion_url)
		val result = DailyMotionParser.search(getHtmlString(), utils.getValue(tag_title), utils.getValue(attr_title),
				utils.getValue(suffix_title))

		Assert.assertEquals(result, testAnswer)

	}

	private fun getHtmlString(): String {
		return "<html><head></head><body><script id='page-data'>$testAnswer</script></body></html>"
	}
}