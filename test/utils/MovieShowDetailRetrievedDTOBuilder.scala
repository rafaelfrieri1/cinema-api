import models.MovieShowDetailRetrievedDTO

import java.time.LocalTime

object MovieShowDetailRetrievedDTOBuilder extends Randoms {

  val base = MovieShowDetailRetrievedDTO(
    id = nextRandomInt(),
    showTime = LocalTime.MIN,
    price = nextRandomBigDecimal()
  )
}
