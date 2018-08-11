package exceptions

import play.api.libs.json.{JsPath, JsonValidationError}

case class RequestBodyParseException(errors: List[(JsPath, List[JsonValidationError])]) extends Exception

object RequestBodyParseException
{
  def create(errors: Seq[(JsPath, Seq[JsonValidationError])]): RequestBodyParseException =
    RequestBodyParseException {
      errors.map {
        case (path, validationErrors) => path -> validationErrors.toList
      }
        .toList
    }
}
