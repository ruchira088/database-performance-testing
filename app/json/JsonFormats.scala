package json

import org.joda.time.DateTime
import org.joda.time.chrono.ISOChronology
import play.api.libs.json._

import scala.util.Try

object JsonFormats
{
  implicit object JodaTimeFormat extends Format[DateTime]
  {
    override def reads(jsValue: JsValue): JsResult[DateTime] =
      jsValue match {
        case JsString(value) =>
          Try(DateTime.parse(value))
            .fold(
              throwable => JsError(throwable.getMessage),
              dateTime => JsSuccess(dateTime.withChronology(ISOChronology.getInstance()))
            )

        case _ => JsError("Mismatched JsValue type")
      }

    override def writes(dateTime: DateTime): JsValue =
      JsString(dateTime.toString)
  }
}
