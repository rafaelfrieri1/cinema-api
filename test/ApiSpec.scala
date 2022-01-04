import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.{Application, Mode}
import play.api.inject.Binding
import play.api.inject.guice.GuiceApplicationBuilder

import scala.reflect.ClassTag

abstract class ApiSpec extends PlaySpec with GuiceOneAppPerTest with MockitoSugar {

  protected val unit: Unit = ()

  override def fakeApplication(): Application = {
    new GuiceApplicationBuilder()
      .in(Mode.Test)
      .configure(configuration())
      .overrides(bindings())
      .build()
  }

  protected def bindings(): Seq[Binding[_ <: Any]] = Seq.empty

  protected def configuration(): Map[String, _ <: Any] = Map.empty

  protected def instanceOf[T: ClassTag](): T = app.injector.instanceOf[T]
}
