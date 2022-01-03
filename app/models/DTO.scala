package models

import play.api.libs.json._
import utils.dto.DTOOperations._
import java.time.LocalTime

import scala.math.BigDecimal

case class ExceptionDTO(
  statusCode: Int,
  errorMessage: String
)

case class MovieShowDetailDTO(
  showTime: LocalTime,
  price: BigDecimal
)
