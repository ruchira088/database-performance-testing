package services.tiny

import models.TinyUrl
import web.requests.CreateTinyUrlRequest

import scala.concurrent.{ExecutionContext, Future}

trait TinyUrlService
{
  def insert(createTinyUrlRequest: CreateTinyUrlRequest)
            (implicit executionContext: ExecutionContext): Future[TinyUrl]

  def get(key: String)(implicit executionContext: ExecutionContext): Future[TinyUrl]
}
