package models

import javax.inject.Inject
import models.Tables.{MovieRatings, MovieRatingsRow}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class MovieRatingModelImpl @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext) extends MovieRatingModel with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val movieRatings = TableQuery[MovieRatings]

  def getId(table: MovieRatings): Rep[Int] = table.id

  private def filterById(id: Int): Query[MovieRatings, MovieRatingsRow, Seq] = movieRatings.filter(mr => getId(mr) === id)

  def add(movieRating: MovieRatingsRow): Future[Int] = db run ((movieRatings returning movieRatings.map(getId)) += movieRating)

  def findByMovieId(movieId: Int): Future[Seq[MovieRatingsRow]] =
    db run movieRatings.filter(mr => mr.movieId === movieId && mr.active).result
}
