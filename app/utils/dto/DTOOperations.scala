package utils.dto

import models._
import play.api.libs.json._

object DTOOperations {
  implicit val movieShowDetailDTOJsonReads: Reads[MovieShowDetailDTO] = Json.reads[MovieShowDetailDTO]
}
