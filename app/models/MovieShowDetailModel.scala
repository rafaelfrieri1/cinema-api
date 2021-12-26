package models

import scala.concurrent.Future
import models.Tables.MovieShowDetailsRow

trait MovieShowDetailModel {
  def add(movieShowDetail: MovieShowDetailsRow): Future[Int]
}
