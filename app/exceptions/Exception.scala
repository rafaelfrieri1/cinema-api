package exceptions

import play.api.libs.json.{JsObject, Json}

trait ApiException extends Exception {
  def statusCode: Int

  def errorMessage: String
}

case class InvalidBodyException() extends ApiException {
  def statusCode: Int = 400

  def errorMessage: String = "error.invalid.data"
}

case class MovieNotFoundException() extends ApiException {
  def statusCode: Int = 404

  def errorMessage: String = "error.movie.not.found"
}

case class MovieShowDetailAlreadyExistsException() extends ApiException {
  def statusCode: Int = 400

  def errorMessage: String = "error.movie.show.detail.already.exists"
}