package models

import scala.concurrent.Future
import models.Tables.MoviesRow

trait MovieModel {
  def add(movie: MoviesRow): Future[Int]
  def findById(movieId: Int): Future[Option[MoviesRow]]
}
