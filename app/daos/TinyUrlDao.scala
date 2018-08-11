package daos

import models.TinyUrl
import utils.FutureOpt

import scala.concurrent.{ExecutionContext, Future}

trait TinyUrlDao
{
  def insert(tinyUrl: TinyUrl)(implicit executionContext: ExecutionContext): Future[TinyUrl]

  def getByKey(key: String)(implicit executionContext: ExecutionContext): FutureOpt[TinyUrl]
}