package helpers

import exceptions.{ApiException, InvalidBodyException}
import javax.inject.Inject
import play.api.Configuration
import play.api.libs.json.JsResult
import scala.concurrent.{ExecutionContext, Future}

class ValidationHelperImpl @Inject()(
  configuration: Configuration
)(implicit val ec: ExecutionContext) extends ValidationHelper {

  def validateBody[A](dto: JsResult[A]): Future[Either[ApiException, A]] = {
    Future.successful{
      dto.fold(
        error => Left(InvalidBodyException()),
        data => Right(data)
      )
    }
  }

}
