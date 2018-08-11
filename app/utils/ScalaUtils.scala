package utils

import scala.util.{Failure, Success, Try}

object ScalaUtils
{
  def toTry[A](option: Option[A], throwable: Throwable): Try[A] =
    option.fold[Try[A]](Failure(throwable))(Success.apply)
}