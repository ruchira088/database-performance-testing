package services.tiny

import daos.TinyUrlDao
import exceptions.UrlKeyNotFoundException
import javax.inject.{Inject, Singleton}
import models.TinyUrl
import org.joda.time.DateTime
import services.shorten.UrlKeyGenerationService
import services.shorten.UrlKeyGenerationService.randomString
import web.requests.CreateTinyUrlRequest

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TinyUrlServiceImpl @Inject()(tinyUrlDao: TinyUrlDao, urlKeyGenerationService: UrlKeyGenerationService)
  extends TinyUrlService
{
  override def insert(createTinyUrlRequest: CreateTinyUrlRequest)
                     (implicit executionContext: ExecutionContext): Future[TinyUrl] =
    for {
      key <- urlKeyGenerationService.generateKey(createTinyUrlRequest.url)
      tinyUrl <- insert(createTinyUrlRequest, key)
    }
    yield tinyUrl

  def insert(createTinyUrlRequest: CreateTinyUrlRequest, key: String)
                     (implicit executionContext: ExecutionContext): Future[TinyUrl] =
    tinyUrlDao.getByKey(key)
      .fold(tinyUrlDao.insert(TinyUrl(key, DateTime.now(), createTinyUrlRequest.url))) {
        _ =>
          for {
            newKey <- urlKeyGenerationService.generateKey(randomString(1) + createTinyUrlRequest.url)
            tinyUrl <- insert(createTinyUrlRequest, newKey)
          }
          yield tinyUrl
      }

  override def get(key: String)(implicit executionContext: ExecutionContext): Future[TinyUrl] =
    tinyUrlDao.getByKey(key)
      .fold(Future.failed(UrlKeyNotFoundException(key)))(Future.successful)
}
