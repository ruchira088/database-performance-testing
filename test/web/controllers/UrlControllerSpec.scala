package web.controllers

import com.github.javafaker.Faker
import daos.TinyUrlDao
import models.TinyUrl
import org.joda.time.DateTime
import play.api.http.ContentTypes
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import utils.PlayAppSpec
import utils.PlayAppSpec._

import scala.concurrent.ExecutionContext.Implicits.global

class UrlControllerSpec extends PlayAppSpec
{
  "POST /tiny-url" should "return a tiny url key" in {

    val application = applicationBuilder().build()

    val url = Faker.instance().internet().url()
    val requestBody = Json.parse(s"""{ "url": "$url" }""")
    val request = fakePostRequest("/tiny-url", requestBody)

    val response = route(application, request).value

    status(response) mustBe CREATED
    contentType(response).value mustBe ContentTypes.JSON

    val createdTinyUrl = contentAsJson(response).validate[TinyUrl].asOpt.value

    val tinyUrlDao = application.injector.instanceOf[TinyUrlDao]
    val retrievedTinyUrl = waitForFuture(tinyUrlDao.getByKey(createdTinyUrl.key).value).value

    createdTinyUrl mustBe retrievedTinyUrl

    waitForFuture(application.stop())
  }

  "GET /tiny-url/:key" should "redirect the HTTP request to the appropriate URL" in {

    val application = applicationBuilder().build()

    val tinyUrl = TinyUrl("db-performance", DateTime.now(), "https://github.com/ruchira088/database-performance-testing")
    val tinyUrlDao = application.injector.instanceOf[TinyUrlDao]

    waitForFuture(tinyUrlDao.insert(tinyUrl))

    val request = FakeRequest(GET, s"/tiny-url/${tinyUrl.key}")
    val response = route(application, request).value

    status(response) mustBe SEE_OTHER
    redirectLocation(response).value mustBe tinyUrl.url

    waitForFuture(application.stop())
  }

}
