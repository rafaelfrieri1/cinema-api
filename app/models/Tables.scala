package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = MovieRatings.schema ++ Movies.schema ++ MovieShowDetails.schema ++ PlayEvolutions.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table MovieRatings
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param movieId Database column movie_id SqlType(INT)
   *  @param rating Database column rating SqlType(DECIMAL)
   *  @param active Database column active SqlType(BIT), Default(true)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param deleted Database column deleted SqlType(TIMESTAMP), Default(None) */
  case class MovieRatingsRow(id: Int, movieId: Int, rating: scala.math.BigDecimal, active: Boolean = true, created: java.sql.Timestamp, deleted: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching MovieRatingsRow objects using plain SQL queries */
  implicit def GetResultMovieRatingsRow(implicit e0: GR[Int], e1: GR[scala.math.BigDecimal], e2: GR[Boolean], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[MovieRatingsRow] = GR{
    prs => import prs._
    MovieRatingsRow.tupled((<<[Int], <<[Int], <<[scala.math.BigDecimal], <<[Boolean], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table movie_ratings. Objects of this class serve as prototypes for rows in queries. */
  class MovieRatings(_tableTag: Tag) extends profile.api.Table[MovieRatingsRow](_tableTag, Some("cinema"), "movie_ratings") {
    def * = (id, movieId, rating, active, created, deleted) <> (MovieRatingsRow.tupled, MovieRatingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(movieId), Rep.Some(rating), Rep.Some(active), Rep.Some(created), deleted)).shaped.<>({r=>import r._; _1.map(_=> MovieRatingsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column movie_id SqlType(INT) */
    val movieId: Rep[Int] = column[Int]("movie_id")
    /** Database column rating SqlType(DECIMAL) */
    val rating: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("rating")
    /** Database column active SqlType(BIT), Default(true) */
    val active: Rep[Boolean] = column[Boolean]("active", O.Default(true))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created")
    /** Database column deleted SqlType(TIMESTAMP), Default(None) */
    val deleted: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("deleted", O.Default(None))

    /** Foreign key referencing Movies (database name movie_id_ratings_fk) */
    lazy val moviesFk = foreignKey("movie_id_ratings_fk", movieId, Movies)(r => r.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table MovieRatings */
  lazy val MovieRatings = new TableQuery(tag => new MovieRatings(tag))

  /** Entity class storing rows of table Movies
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(100,true)
   *  @param imdbId Database column imdb_id SqlType(VARCHAR), Length(30,true)
   *  @param active Database column active SqlType(BIT), Default(true)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param deleted Database column deleted SqlType(TIMESTAMP), Default(None) */
  case class MoviesRow(id: Int, name: String, imdbId: String, active: Boolean = true, created: java.sql.Timestamp, deleted: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching MoviesRow objects using plain SQL queries */
  implicit def GetResultMoviesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[MoviesRow] = GR{
    prs => import prs._
    MoviesRow.tupled((<<[Int], <<[String], <<[String], <<[Boolean], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table movies. Objects of this class serve as prototypes for rows in queries. */
  class Movies(_tableTag: Tag) extends profile.api.Table[MoviesRow](_tableTag, Some("cinema"), "movies") {
    def * = (id, name, imdbId, active, created, deleted) <> (MoviesRow.tupled, MoviesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(imdbId), Rep.Some(active), Rep.Some(created), deleted)).shaped.<>({r=>import r._; _1.map(_=> MoviesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column imdb_id SqlType(VARCHAR), Length(30,true) */
    val imdbId: Rep[String] = column[String]("imdb_id", O.Length(30,varying=true))
    /** Database column active SqlType(BIT), Default(true) */
    val active: Rep[Boolean] = column[Boolean]("active", O.Default(true))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created")
    /** Database column deleted SqlType(TIMESTAMP), Default(None) */
    val deleted: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("deleted", O.Default(None))
  }
  /** Collection-like TableQuery object for table Movies */
  lazy val Movies = new TableQuery(tag => new Movies(tag))

  /** Entity class storing rows of table MovieShowDetails
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param movieId Database column movie_id SqlType(INT)
   *  @param showTime Database column show_time SqlType(TIME)
   *  @param price Database column price SqlType(DECIMAL)
   *  @param active Database column active SqlType(BIT), Default(true)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param deleted Database column deleted SqlType(TIMESTAMP), Default(None) */
  case class MovieShowDetailsRow(id: Int, movieId: Int, showTime: java.sql.Time, price: scala.math.BigDecimal, active: Boolean = true, created: java.sql.Timestamp, deleted: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching MovieShowDetailsRow objects using plain SQL queries */
  implicit def GetResultMovieShowDetailsRow(implicit e0: GR[Int], e1: GR[java.sql.Time], e2: GR[scala.math.BigDecimal], e3: GR[Boolean], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[MovieShowDetailsRow] = GR{
    prs => import prs._
    MovieShowDetailsRow.tupled((<<[Int], <<[Int], <<[java.sql.Time], <<[scala.math.BigDecimal], <<[Boolean], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table movie_show_details. Objects of this class serve as prototypes for rows in queries. */
  class MovieShowDetails(_tableTag: Tag) extends profile.api.Table[MovieShowDetailsRow](_tableTag, Some("cinema"), "movie_show_details") {
    def * = (id, movieId, showTime, price, active, created, deleted) <> (MovieShowDetailsRow.tupled, MovieShowDetailsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(movieId), Rep.Some(showTime), Rep.Some(price), Rep.Some(active), Rep.Some(created), deleted)).shaped.<>({r=>import r._; _1.map(_=> MovieShowDetailsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column movie_id SqlType(INT) */
    val movieId: Rep[Int] = column[Int]("movie_id")
    /** Database column show_time SqlType(TIME) */
    val showTime: Rep[java.sql.Time] = column[java.sql.Time]("show_time")
    /** Database column price SqlType(DECIMAL) */
    val price: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("price")
    /** Database column active SqlType(BIT), Default(true) */
    val active: Rep[Boolean] = column[Boolean]("active", O.Default(true))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created")
    /** Database column deleted SqlType(TIMESTAMP), Default(None) */
    val deleted: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("deleted", O.Default(None))

    /** Foreign key referencing Movies (database name movie_id_fk) */
    lazy val moviesFk = foreignKey("movie_id_fk", movieId, Movies)(r => r.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table MovieShowDetails */
  lazy val MovieShowDetails = new TableQuery(tag => new MovieShowDetails(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, Some("cinema"), "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem)).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))
}
