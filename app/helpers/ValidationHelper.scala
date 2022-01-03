package helpers

import exceptions.ApiException
import play.api.libs.json.JsResult

import scala.concurrent.Future

trait ValidationHelper {
  def validateBody[A](dto: JsResult[A]): Future[Either[ApiException, A]]
}
