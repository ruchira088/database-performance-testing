package utils

import play.api.libs.json.{JsValue, Reads}
import play.api.mvc.Request

import scala.util.{Success, Try}

object RequestUtils
{
  def parseRequest[A](implicit request: Request[JsValue], reads: Reads[A]): Try[A] =
    request.body.validate[A].fold[Try[A]](???, Success.apply)
}