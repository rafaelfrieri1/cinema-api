package models

import scala.concurrent.Future
import models.Tables.MovieShowDetailsRow

import java.sql.Time

trait MovieShowDetailModel {
  def add(movieShowDetail: MovieShowDetailsRow): Future[Int]
  def findByShowTime(movieShowTime: Time): Future[Option[MovieShowDetailsRow]]
}
