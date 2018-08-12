package utils

import akka.util.Timeout
import org.scalatest.{FlatSpec, MustMatchers, OptionValues}
import play.api.http.{ContentTypes, HeaderNames, HttpVerbs}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.JsValue
import play.api.mvc.AnyContentAsJson
import play.api.test.{FakeHeaders, FakeRequest}

import scala.concurrent.{Await, Future}

trait PlayAppSpec extends FlatSpec with MustMatchers with OptionValues

object PlayAppSpec
{
  def applicationBuilder(): GuiceApplicationBuilder = new GuiceApplicationBuilder()

  def fakePostRequest(url: String, jsonBody: JsValue) =
    FakeRequest(
      HttpVerbs.POST,
      url,
      FakeHeaders(List(HeaderNames.HOST -> "localhost", HeaderNames.CONTENT_TYPE -> ContentTypes.JSON)),
      AnyContentAsJson(jsonBody)
    )

  def waitForFuture[A](future: Future[A])(implicit timeout: Timeout): A =
    Await.result(future, timeout.duration)
}