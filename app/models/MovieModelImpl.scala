package models

import javax.inject.Inject
import models.Tables.{Movies, MoviesRow}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class MovieModelImpl @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext) extends MovieModel with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val movies = TableQuery[Movies]

  def getId(table: Movies): Rep[Int] = table.id

  private def filterById(id: Int): Query[Movies, MoviesRow, Seq] = movies.filter(m => getId(m) === id)

  def add(movie: MoviesRow): Future[Int] = db run ((movies returning movies.map(getId)) += movie)
}
