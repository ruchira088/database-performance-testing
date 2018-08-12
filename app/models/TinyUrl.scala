package models

import json.JsonFormats.JodaTimeFormat
import org.joda.time.DateTime
import play.api.libs.json.{Json, OFormat}

case class TinyUrl(key: String, createdAt: DateTime, url: String)

object TinyUrl
{
  implicit val tinyUrlFormat: OFormat[TinyUrl] = Json.format[TinyUrl]
}