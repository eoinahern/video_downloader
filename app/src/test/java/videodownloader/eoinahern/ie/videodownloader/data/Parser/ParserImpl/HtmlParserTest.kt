package videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserImpl

import de.jodamob.kotlin.testrunner.KotlinTestRunner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(KotlinTestRunner::class)
 class HtmlParserTest {

	private val url: String = "www.google.com/5yryeywj.mp4"

	/**
	 * test correct url returned
	 */
	@Test
	fun searchTest() {

		var link = HtmlParser.search(getHtmlString())
		Assert.assertEquals(url, link)
	}

	/**
	 * fake html
	 */
	private fun getHtmlString(): String {
		return "<html><head></head><body><source src=$url></source></body></html>"
	}

	@Test
	fun regexTest() {

		val str  = "fhfhfhfhfhfhfhffhfhffhf ////// \"\"type\":\"video\\/mp4\",\"url\":\"http:\\/\\/www.dailymotion.com\\/cdn\\/H264-176x144-2\\/video\\/x6fc4dc.mp4?auth=1521132498-2562-ga7ux10t-40692caf93ab4dc184947e523f4e1ba9\"}],\"240\":////"
		val regex = """http:\\/\\/[a-zA-z./0-9-]*(\?auth=[0-9a-zA-z-]*)?""".toRegex()


		val found =  regex.find(str)
		println(found?.value)

		Assert.assertEquals(found?.value, "http:\\/\\/www.dailymotion.com\\/cdn\\/H264-176x144-2\\/video\\/x6fc4dc.mp4?auth=1521132498-2562-ga7ux10t-40692caf93ab4dc184947e523f4e1ba9")
		Assert.assertEquals(found?.value?.replace("\\", ""), "http://www.dailymotion.com/cdn/H264-176x144-2/video/x6fc4dc.mp4?auth=1521132498-2562-ga7ux10t-40692caf93ab4dc184947e523f4e1ba9")
	}


}