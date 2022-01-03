package controllers

import exceptions.ApiException
import models.ExceptionDTO
import play.api.i18n.{I18nSupport, Messages}
import play.api.libs.json.Json
import play.api.mvc.Results.Status
import play.api.mvc.{RequestHeader, Result}

trait ApiExceptionHandler extends I18nSupport {

  def apiExceptionToResult(exception: ApiException)(implicit request: RequestHeader): Result = {
    new Status(exception.statusCode)(Json.format[ExceptionDTO].writes(ExceptionDTO(
      statusCode = exception.statusCode,
      errorMessage = Messages(exception.errorMessage)
    )))
  }

}
