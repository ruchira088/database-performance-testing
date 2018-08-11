package web.exceptions

import play.api.mvc.Result

import scala.concurrent.{ExecutionContext, Future}

object ServerExceptions
{
  def handleExceptions(result: Future[Result])(implicit executionContext: ExecutionContext): Future[Result] =
    result.recover {
      case _ => ???
    }
}
