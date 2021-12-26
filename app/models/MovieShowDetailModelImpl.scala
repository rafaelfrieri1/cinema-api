package models

import javax.inject.Inject
import models.Tables.{MovieShowDetails, MovieShowDetailsRow}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class MovieShowDetailModelImpl @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext) extends MovieShowDetailModel with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val movieShowDetails = TableQuery[MovieShowDetails]

  def getId(table: MovieShowDetails): Rep[Int] = table.id

  private def filterById(id: Int): Query[MovieShowDetails, MovieShowDetailsRow, Seq] = movieShowDetails.filter(msd => getId(msd) === id)

  def add(movieShowDetail: MovieShowDetailsRow): Future[Int] = db run ((movieShowDetails returning movieShowDetails.map(getId)) += movieShowDetail)
}
