package models

import scala.concurrent.Future
import models.Tables.MovieRatingsRow

trait MovieRatingModel {
  def add(movie: MovieRatingsRow): Future[Int]
  def findById(movieRatingId: Int): Future[Option[MovieRatingsRow]]
  def findByMovieId(movieId: Int): Future[Seq[MovieRatingsRow]]
  def update(movieRatingId: Int, movieRating: MovieRatingsRow): Future[Unit]
}
