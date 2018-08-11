package utils

import exceptions.RequestBodyParseException
import play.api.libs.json.{JsValue, Reads}
import play.api.mvc.Request

import scala.util.{Failure, Success, Try}

object RequestUtils
{
  def parseRequest[A](implicit request: Request[JsValue], reads: Reads[A]): Try[A] =
    request.body.validate[A]
      .fold[Try[A]](
        validationErrors => Failure(RequestBodyParseException.create(validationErrors)),
        Success.apply
      )
}