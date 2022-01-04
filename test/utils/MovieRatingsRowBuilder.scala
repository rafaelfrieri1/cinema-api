import models.Tables.MovieRatingsRow

import java.sql.Timestamp

object MovieRatingsRowBuilder extends Randoms {

  val base = MovieRatingsRow(
    id = nextRandomInt(),
    movieId = nextRandomInt(),
    rating = nextRandomBigDecimal(),
    active = true,
    created = new Timestamp(System.currentTimeMillis),
    deleted = None
  )
}
