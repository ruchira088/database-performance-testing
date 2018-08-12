package utils

import akka.util.Timeout
import org.scalatest.{FlatSpec, MustMatchers, OptionValues}
import play.api.inject.guice.GuiceApplicationBuilder

import scala.concurrent.{Await, Future}

trait PlayAppSpec extends FlatSpec with MustMatchers with OptionValues

object PlayAppSpec
{
  def applicationBuilder(): GuiceApplicationBuilder = new GuiceApplicationBuilder()

  def waitForFuture[A](future: Future[A])(implicit timeout: Timeout): A =
    Await.result(future, timeout.duration)
}