import models.Tables.MoviesRow

import java.sql.Timestamp

object MoviesRowBuilder extends Randoms {

  val base = MoviesRow(
    id = nextRandomInt(),
    name = nextRandomString(),
    imdbId = nextRandomString(),
    active = true,
    created = new Timestamp(System.currentTimeMillis),
    deleted = None
  )
}
