package modules

import com.google.inject.AbstractModule
import helpers._
import models._
import services._
import net.codingwell.scalaguice.ScalaModule
import play.api.libs.concurrent.AkkaGuiceSupport
import play.api.{Configuration, Environment}

class BasicModule(environment: Environment, configuration: Configuration) extends AbstractModule with ScalaModule with AkkaGuiceSupport {

  override def configure(): Unit = {
    // Helpers
    bind[ValidationHelper].to[ValidationHelperImpl]

    // Models
    bind[MovieModel].to[MovieModelImpl]
    bind[MovieShowDetailModel].to[MovieShowDetailModelImpl]

    // Services
    bind[MovieService].to[MovieServiceImpl]
  }

}
