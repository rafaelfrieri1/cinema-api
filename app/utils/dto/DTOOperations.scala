package utils.dto

import models._
import play.api.libs.json._

object DTOOperations {
  implicit val movieShowDetailDTOJsonReads: Reads[MovieShowDetailDTO] = Json.reads[MovieShowDetailDTO]
  implicit val movieShowDetailRetrievedDTOJsonWrites: Writes[MovieShowDetailRetrievedDTO] = Json.writes[MovieShowDetailRetrievedDTO]
  implicit val movieShowDetailsDTOJsonWrites: Writes[MovieShowDetailsDTO] = Json.writes[MovieShowDetailsDTO]
  implicit val movieShowDetailEditDTOJsonReads: Reads[MovieShowDetailEditDTO] = Json.reads[MovieShowDetailEditDTO]
  implicit val movieDetailsDTOJsonWrites: Writes[MovieDetailsDTO] = Json.writes[MovieDetailsDTO]
  implicit val movieRatingDTOJsonReads: Reads[MovieRatingDTO] = Json.reads[MovieRatingDTO]
    .filter(JsonValidationError("error.invalid.value", "rating"))(MovieRatingDTO.validateRating)
  implicit val movieRatingDTOJsonWrites: Writes[MovieRatingDTO] = Json.writes[MovieRatingDTO]
  implicit val movieRatingRetrievedDTOJsonWrites: Writes[MovieRatingRetrievedDTO] = Json.writes[MovieRatingRetrievedDTO]
  implicit val movieRatingsDTOJsonWrites: Writes[MovieRatingsDTO] = Json.writes[MovieRatingsDTO]
  implicit val movieRatingEditDTOJsonReads: Reads[MovieRatingEditDTO] = Json.reads[MovieRatingEditDTO]
    .filter(JsonValidationError("error.invalid.value", "rating"))(MovieRatingEditDTO.validateRating)
}
