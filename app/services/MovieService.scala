package services

import models.{MovieDetailsDTO, MovieShowDetailsDTO, MovieShowDetailRetrievedDTO}

import java.time.LocalTime

import scala.concurrent.Future
import scala.math.BigDecimal

trait MovieService {
  def addMovieShowTime(movieId: Int, showTime: LocalTime, price: BigDecimal): Future[Int]
  def deleteMovieShowTime(movieId: Int, movieShowTimeId: Int): Future[Unit]
  def editMovieShowTime(
    movieId: Int,
    movieShowTimeId: Int,
    showTime: Option[LocalTime],
    price: Option[BigDecimal]
  ): Future[MovieShowDetailRetrievedDTO]
  def getMovieShowTimes(movieId: Int): Future[MovieShowDetailsDTO]
  def checkMovieDetails(movieId: Int): Future[MovieDetailsDTO]
}
