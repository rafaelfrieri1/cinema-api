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

case class MovieShowDetailRetrievedDTO(
  id: Int,
  showTime: LocalTime,
  price: BigDecimal
)

case class MovieShowDetailsDTO(
  name: String,
  showDetails: Seq[MovieShowDetailRetrievedDTO]
)

case class MovieShowDetailEditDTO(
  showTime: Option[LocalTime],
  price: Option[BigDecimal]
)
