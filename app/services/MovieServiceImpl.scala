package services

import exceptions._
import javax.inject.Inject
import models.{MovieModel, MovieShowDetailModel}
import models.Tables.MovieShowDetailsRow
import play.api.Configuration

import java.sql.Time
import java.sql.Timestamp
import java.time.{LocalDate, LocalDateTime, LocalTime, ZoneId, ZoneOffset}
import scala.concurrent.{ExecutionContext, Future}

class MovieServiceImpl @Inject()(
  movieModel: MovieModel,
  movieShowDetailModel: MovieShowDetailModel
)(implicit ec: ExecutionContext, configuration: Configuration) extends MovieService {

  def addMovieShowTime(movieId: Int, showTime: LocalTime, price: BigDecimal): Future[Int] = {
    val showTimeAdjusted = LocalDateTime.of(LocalDate.now(), showTime)
      .atZone(ZoneId.systemDefault)
      .withZoneSameInstant(ZoneOffset.UTC)
      .toLocalTime();
    
    val showTimeSQL = Time.valueOf(showTime)
    val showTimeSQLAdjusted = Time.valueOf(showTimeAdjusted)

    for {
      movieExistence <- checkMovieExists(movieId)
      movieShowDetailExistence <- checkShowExists(showTimeSQLAdjusted)
      movieShowDetailId <- (movieExistence, movieShowDetailExistence) match {
        case (true, false) =>
          movieShowDetailModel.add(
            MovieShowDetailsRow(
              id = 1,
              movieId = movieId,
              showTime = showTimeSQL,
              price = Some(price),
              created = new Timestamp(System.currentTimeMillis())
            )
          )
        case (false, _) => throw new MovieNotFoundException()
        case (_, true) => throw new MovieShowDetailAlreadyExistsException()
      }
    } yield movieShowDetailId
  }

  private def checkMovieExists(movieId: Int): Future[Boolean] = {
    movieModel.findById(movieId).map {
      case Some(movie) => true
      case None => false
    }
  }

  private def checkShowExists(showTime: Time): Future[Boolean] = {
    movieShowDetailModel.findByShowTime(showTime).map {
      case Some(movieShowDetail) => true
      case None => false
    }
  }

}