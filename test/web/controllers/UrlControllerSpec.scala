package web.controllers

import play.api.test.Helpers._
import utils.PlayAppSpec
import utils.PlayAppSpec._

class UrlControllerSpec extends PlayAppSpec
{
  "POST /tiny-url" should "return a tiny url key" in {

    val application = applicationBuilder().build()

    waitForFuture(application.stop())
  }

}
