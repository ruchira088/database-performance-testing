package web.controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import web.responses.ServiceInformation

@Singleton
class InfoController @Inject()(controllerComponents: ControllerComponents, serviceInformation: ServiceInformation)
  extends AbstractController(controllerComponents)
{
  def serviceInfo() = Action { Ok(Json.toJson(serviceInformation)) }
}