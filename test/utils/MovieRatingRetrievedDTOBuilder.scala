import models.MovieRatingRetrievedDTO

import java.sql.Timestamp

object MovieRatingRetrievedDTOBuilder extends Randoms {

  val base = MovieRatingRetrievedDTO(
    id = nextRandomInt(),
    rating = nextRandomBigDecimal()
  )
}
