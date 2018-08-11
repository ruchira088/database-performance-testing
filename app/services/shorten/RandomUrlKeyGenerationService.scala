package services.shorten
import javax.inject.{Inject, Singleton}
import services.config.ConfigurationService
import services.shorten.UrlKeyGenerationService.randomString

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RandomUrlKeyGenerationService @Inject()(configurationService: ConfigurationService)
  extends UrlKeyGenerationService
{
  override def generateKey(longUrl: String)(implicit executionContext: ExecutionContext): Future[String] =
    Future.successful {
      randomString(configurationService.urlKeyLength())
    }
}