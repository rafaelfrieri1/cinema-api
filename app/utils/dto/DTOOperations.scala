package utils.dto

import models._
import play.api.libs.json._

object DTOOperations {
  implicit val movieShowDetailDTOJsonReads: Reads[MovieShowDetailDTO] = Json.reads[MovieShowDetailDTO]
  implicit val movieShowDetailRetrievedDTOJsonWrites: Writes[MovieShowDetailRetrievedDTO] = Json.writes[MovieShowDetailRetrievedDTO]
  implicit val movieShowDetailsDTOJsonWrites: Writes[MovieShowDetailsDTO] = Json.writes[MovieShowDetailsDTO]
  implicit val movieShowDetailEditDTOJsonReads: Reads[MovieShowDetailEditDTO] = Json.reads[MovieShowDetailEditDTO]
  implicit val movieDetailsDTOJsonWrites: Writes[MovieDetailsDTO] = Json.writes[MovieDetailsDTO]
}
