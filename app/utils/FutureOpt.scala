package utils

import scala.concurrent.{ExecutionContext, Future}

case class FutureOpt[+A](value: Future[Option[A]])
{
  def flatMap[B](f: A => FutureOpt[B])(implicit executionContext: ExecutionContext): FutureOpt[B] =
    FutureOpt[B] {
      value.flatMap {
        case None => Future.successful(None)
        case Some(result) => f(result).value
      }
    }

  def map[B](f: A => B)(implicit executionContext: ExecutionContext): FutureOpt[B] =
    flatMap(result => FutureOpt.unit(f(result)))

  def fold[B](empty: => Future[B])(nonEmpty: A => Future[B])(implicit executionContext: ExecutionContext): Future[B] =
    value.flatMap {
      case None => empty
      case Some(result) => nonEmpty(result)
    }
}

object FutureOpt
{
  def unit[A](value: A): FutureOpt[A] = FutureOpt[A](Future.successful(Some(value)))
}
