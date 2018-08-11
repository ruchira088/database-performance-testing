package web.requests

import play.api.libs.json.{Json, OFormat}

case class CreateTinyUrlRequest(url: String)

object CreateTinyUrlRequest
{
  implicit val createTinyUrlRequestFormat: OFormat[CreateTinyUrlRequest] = Json.format[CreateTinyUrlRequest]
}
