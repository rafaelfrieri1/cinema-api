package services

import java.time.LocalTime

import scala.concurrent.Future
import scala.math.BigDecimal

trait MovieService {
  def addMovieShowTime(movieId: Int, showTime: LocalTime, price: BigDecimal): Future[Int]
}