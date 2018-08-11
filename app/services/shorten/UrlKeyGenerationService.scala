package services.shorten

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

trait UrlKeyGenerationService
{
  def generateKey(longUrl: String)(implicit executionContext: ExecutionContext): Future[String]
}

object UrlKeyGenerationService
{
  def randomString(length: Int): String = Random.alphanumeric.take(length).toList.mkString
}