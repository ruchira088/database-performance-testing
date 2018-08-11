package modules

import com.google.inject.AbstractModule
import daos.{SlickTinyUrlDao, TinyUrlDao}
import services.shorten.{RandomUrlKeyGenerationService, UrlKeyGenerationService}
import services.tiny.{TinyUrlService, TinyUrlServiceImpl}
import web.responses.ServiceInformation

class EnvironmentModule extends AbstractModule
{
  override def configure(): Unit =
  {
    bind(classOf[ServiceInformation]).toInstance(ServiceInformation())
    bind(classOf[TinyUrlDao]).to(classOf[SlickTinyUrlDao])
    bind(classOf[UrlKeyGenerationService]).to(classOf[RandomUrlKeyGenerationService])
    bind(classOf[TinyUrlService]).to(classOf[TinyUrlServiceImpl])
  }
}
