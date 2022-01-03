package services

import models._

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
  def addMovieRating(movieId: Int, rating: Int): Future[Int]
  def getMovieRatings(movieId: Int): Future[MovieRatingsDTO]
  def editMovieRating(movieId: Int, movieRatingId: Int, rating: Option[Int]): Future[MovieRatingRetrievedDTO]
  def deleteMovieRating(movieId: Int, movieRatingId: Int): Future[Unit]
}
