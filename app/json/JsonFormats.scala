package json

import org.joda.time.DateTime
import play.api.libs.json._

import scala.util.Try

object JsonFormats
{
  implicit object JodaTimeFormat extends Format[DateTime]
  {
    override def reads(jsValue: JsValue): JsResult[DateTime] =
      jsValue match {
        case JsString(value) =>
          Try(DateTime.parse(value)).fold(throwable => JsError(throwable.getMessage), dateTime => JsSuccess(dateTime))

        case _ => JsError("")
      }

    override def writes(dateTime: DateTime): JsValue =
      JsString(dateTime.toString)
  }
}
