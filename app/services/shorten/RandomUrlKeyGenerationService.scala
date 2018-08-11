package services.shorten
import services.shorten.UrlKeyGenerationService.randomString

import scala.concurrent.{ExecutionContext, Future}

object RandomUrlKeyGenerationService extends UrlKeyGenerationService
{
  override def generateKey(longUrl: String)(implicit executionContext: ExecutionContext): Future[String] =
    Future.successful {
      randomString(8)
    }
}