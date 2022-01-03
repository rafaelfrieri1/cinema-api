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

case class MovieDetailsDTO(
  name: String,
  description: String,
  releaseDate: String,
  metaScore: BigDecimal,
  imdbRating: BigDecimal,
  runTime: String
)

case class MovieRatingDTO(
  rating: Int
)

object MovieRatingDTO {

  private val validRatings = Set(1, 2, 3, 4, 5)

  def validateRating(movieRating: MovieRatingDTO): Boolean = validRatings.contains(movieRating.rating)

}

case class MovieRatingRetrievedDTO(
  id: Int,
  rating: BigDecimal
)

case class MovieRatingsDTO(
  name: String,
  movieRatings: Seq[MovieRatingRetrievedDTO]
)

case class MovieRatingEditDTO(
  rating: Option[Int]
)

object MovieRatingEditDTO {

  private val validRatings = Set(1, 2, 3, 4, 5)

  def validateRating(movieRating: MovieRatingEditDTO): Boolean =
    !movieRating.rating.isDefined || validRatings.contains(movieRating.rating.get)

}
