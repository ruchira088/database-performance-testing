package services.config

import com.typesafe.config.Config
import javax.inject.{Inject, Singleton}

@Singleton
class ConfigurationService @Inject()(config: Config)
{
  def urlKeyLength(): Int = config.getInt("tinyUrl.keyLength")
}