import models.MovieRatingsDTO

import java.sql.Timestamp

object MovieRatingsDTOBuilder extends Randoms {

  val base = MovieRatingsDTO(
    name = nextRandomString(),
    movieRatings = Seq()
  )
}
