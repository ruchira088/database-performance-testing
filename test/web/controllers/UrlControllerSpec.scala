package web.controllers

import com.github.javafaker.Faker
import play.api.http.ContentTypes
import play.api.libs.json.Json
import play.api.test.Helpers._
import utils.PlayAppSpec
import utils.PlayAppSpec._

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
    println(contentAsJson(response))

    waitForFuture(application.stop())
  }

}
