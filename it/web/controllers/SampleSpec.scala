package web.controllers

import org.scalatest.{AsyncFlatSpec, MustMatchers}

import scala.concurrent.Future

class SampleSpec extends AsyncFlatSpec with MustMatchers
{
  "Hello World" should "hello" in Future.successful(1 mustBe 1)
}
