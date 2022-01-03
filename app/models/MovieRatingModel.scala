package models

import scala.concurrent.Future
import models.Tables.MovieRatingsRow

trait MovieRatingModel {
  def add(movie: MovieRatingsRow): Future[Int]
  def findByMovieId(movieId: Int): Future[Seq[MovieRatingsRow]]
}
