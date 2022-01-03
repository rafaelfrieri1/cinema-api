package controllers

import javax.inject.Inject
import exceptions.ApiException
import helpers.ValidationHelper
import models.MovieShowDetailDTO
import services.MovieService
import play.api.Configuration
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import utils.dto.DTOOperations._

import scala.concurrent.{ExecutionContext, Future}

class MoviesController @Inject()(
  validationHelper: ValidationHelper,
  movieService: MovieService,
  configuration: Configuration,
  val controllerComponents: ControllerComponents
)(implicit ec: ExecutionContext) extends BaseController with ApiExceptionHandler {

  def addMovieShowTime(movieId: Int): Action[JsValue] = Action.async(parse.json) { implicit request =>
    val body = request.body.validate[MovieShowDetailDTO]

    validationHelper.validateBody(body).flatMap {
      _.fold(
        exception => Future.successful(apiExceptionToResult(exception)),
        data => movieService.addMovieShowTime(movieId, data.showTime, data.price)
          .map(movieShowDetailId => Ok(Json.toJson(movieShowDetailId)))
          .recover { case exception: ApiException => apiExceptionToResult(exception) }
      )
    }
  }

}
