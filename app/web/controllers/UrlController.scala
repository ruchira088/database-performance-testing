package web.controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.tiny.TinyUrlService
import utils.RequestUtils.parseRequest
import web.requests.CreateTinyUrlRequest

import scala.concurrent.ExecutionContext
import scala.concurrent.Future.fromTry


@Singleton
class UrlController @Inject()(
    controllerComponents: ControllerComponents,
    playBodyParsers: PlayBodyParsers,
    tinyUrlService: TinyUrlService
  )(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents)
{
  def createTinyUrl(): Action[JsValue] =
    Action.async(playBodyParsers.json) {
      implicit request =>
        for {
          createTinyUrlRequest <- fromTry(parseRequest[CreateTinyUrlRequest])
          tinyUrl <- tinyUrlService.insert(createTinyUrlRequest)
        }
        yield Created(Json.toJson(tinyUrl))
    }

  def fetchUrl(key: String): Action[AnyContent] =
    Action.async {
      for {
        tinyUrl <- tinyUrlService.get(key)
      }
      yield Redirect(tinyUrl.longUrl)
    }
}