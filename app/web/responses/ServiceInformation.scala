package web.responses

import com.eed3si9n.BuildInfo._
import play.api.libs.json.{Json, OFormat}

import scala.util.Properties

case class ServiceInformation(
    serviceName: String,
    serviceVersion: String,
    organization: String,
    scalaVersion: String,
    sbtVersion: String,
    javaVersion: String
)

object ServiceInformation
{
  implicit val serviceInformationFormat: OFormat[ServiceInformation] = Json.format[ServiceInformation]

  def apply(): ServiceInformation =
    ServiceInformation(name, version, organization, scalaVersion, sbtVersion, Properties.javaVersion)
}