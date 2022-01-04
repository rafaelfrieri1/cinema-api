import models.Tables.MovieShowDetailsRow

import java.sql.Time
import java.sql.Timestamp

object MovieShowDetailsRowBuilder extends Randoms {

  val base = MovieShowDetailsRow(
    id = nextRandomInt(),
    movieId = nextRandomInt(),
    showTime = Time.valueOf("00:00:00"),
    price = nextRandomBigDecimal(),
    active = true,
    created = new Timestamp(System.currentTimeMillis),
    deleted = None
  )
}
