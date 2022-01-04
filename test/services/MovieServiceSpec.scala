import exceptions._
import org.scalatest.PrivateMethodTester
import org.scalatest.PrivateMethodTester._
import models._
import models.Tables._
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers.any
import play.api.inject.bind
import play.api.test.Helpers._
import services._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.math.BigDecimal

import java.sql.Time

class MovieServiceSpec extends ApiSpec with PrivateMethodTester {


  override def bindings() = Seq (
    bind[MovieModel].toInstance(mock[MovieModel]),
    bind[MovieRatingModel].toInstance(mock[MovieRatingModel]),
    bind[MovieShowDetailModel].toInstance(mock[MovieShowDetailModel]),
    bind[MovieService].to[MovieServiceImpl]
  )

  override def configuration() = Map(
    "omdb.api.baseUrl" -> "http://omdb.url",
    "omdb.api.key" -> "OMDB_API_KEY"
  )

  "deleteMovieShowTime" should {
    "return unit when movie show time is deleted successfully" in {
      val movieId = 1
      val movieShowTimeId = 1

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      val movieShowDetail = MovieShowDetailsRowBuilder.base.copy(
        id = 1,
        movieId = 1,
        price = BigDecimal(15.00)
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(Some(movieShowDetail)))
      when(instanceOf[MovieShowDetailModel]().update(any(), any())).thenReturn(Future.unit)

      val result = await {
        instanceOf[MovieService]().deleteMovieShowTime(movieId, movieShowTimeId)
      }

      result mustBe unit
    }

    "throw MovieNotFoundException when movie doesn't exist" in {
      val movieId = 1
      val movieShowTimeId = 1

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().deleteMovieShowTime(movieId, movieShowTimeId)
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.not.found"
    }

    "throw MovieShowDetailNotFoundException when movie show detail doesn't exist" in {
      val movieId = 1
      val movieShowTimeId = 1

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().deleteMovieShowTime(movieId, movieShowTimeId)
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.show.detail.not.found"
    }

    "throw MovieShowDetailNotBelongsToMovieException when movie show detail does not belong to the movie" in {
      val movieId = 1
      val movieShowTimeId = 1

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      val movieShowDetail = MovieShowDetailsRowBuilder.base.copy(
        id = 1,
        movieId = 2,
        price = BigDecimal(15.00)
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(Some(movieShowDetail)))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().deleteMovieShowTime(movieId, movieShowTimeId)
      }

      exception.statusCode mustBe 400
      exception.errorMessage mustBe "error.movie.show.detail.does.not.belongs.to.movie"
    }
  }

  "editMovieShowTime" should {
    "return the edited movie show detail when it is edited successfully" in {
      val movieId = 1
      val movieShowTimeId = 1
      val price = 4

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      val movieShowDetail = MovieShowDetailsRowBuilder.base.copy(
        id = 1,
        movieId = 1,
        price = BigDecimal(15.00)
      )

      val movieShowDetailRetrieved = MovieShowDetailRetrievedDTOBuilder.base.copy(
        id = 1,
        price = BigDecimal(4.00)
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(Some(movieShowDetail)))
      when(instanceOf[MovieShowDetailModel]().update(any(), any())).thenReturn(Future.unit)

      val result = await {
        instanceOf[MovieService]().editMovieShowTime(movieId, movieShowTimeId, None, Some(price))
      }

      result mustBe movieShowDetailRetrieved
    }

    "throw MovieNotFoundException when movie doesn't exist" in {
      val movieId = 1
      val movieShowTimeId = 1
      val price = 4

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().editMovieShowTime(movieId, movieShowTimeId, None, Some(price))
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.not.found"
    }

    "throw MovieShowDetailNotFoundException when movie show detail doesn't exist" in {
      val movieId = 1
      val movieShowTimeId = 1
      val price = 4

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().editMovieShowTime(movieId, movieShowTimeId, None, Some(price))
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.show.detail.not.found"
    }

    "throw MovieShowDetailNotBelongsToMovieException when movie show detail does not belong to the movie" in {
      val movieId = 1
      val movieShowTimeId = 1
      val price = 4

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      val movieShowDetail = MovieShowDetailsRowBuilder.base.copy(
        id = 1,
        movieId = 2,
        price = BigDecimal(15.00)
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieShowDetailModel]().findById(movieShowTimeId)).thenReturn(Future(Some(movieShowDetail)))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().editMovieShowTime(movieId, movieShowTimeId, None, Some(price))
      }

      exception.statusCode mustBe 400
      exception.errorMessage mustBe "error.movie.show.detail.does.not.belongs.to.movie"
    }
  }

  "addMovieRating" should {
    "return movieRatingId of the movie rating added" in {
      val movieId = 1
      val movieRatingId = 1
      val rating = 3

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieRatingModel]().add(any())).thenReturn(Future(movieRatingId))

      val result = await {
        instanceOf[MovieService]().addMovieRating(movieId, rating)
      }

      result mustBe 1
    }

    "throw MovieNotFoundException when movie doesn't exist" in {
      val movieId = 1
      val rating = 3

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().addMovieRating(movieId, rating)
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.not.found"
    }
  }

  "getMovieRatings" should {
    "return movie ratings successfully" in {
      val movieId = 1

      val movie = MoviesRowBuilder.base.copy(
        id = 1,
        name = "Movie 1",
        imdbId = "Id 1"
      )

      val movieRating1 = MovieRatingsRowBuilder.base.copy(
        id = 1,
        movieId = 1,
        rating = BigDecimal(4.0)
      )

      val movieRating2 = MovieRatingsRowBuilder.base.copy(
        id = 2,
        movieId = 1,
        rating = BigDecimal(3.0)
      )

      val movieRatingRetrieved1 = MovieRatingRetrievedDTOBuilder.base.copy(
        id = 1,
        rating = BigDecimal(4.0)
      )

      val movieRatingRetrieved2 = MovieRatingRetrievedDTOBuilder.base.copy(
        id = 2,
        rating = BigDecimal(3.0)
      )

      val movieRatings = MovieRatingsDTOBuilder.base.copy(
        name = "Movie 1",
        movieRatings = Seq(movieRatingRetrieved1, movieRatingRetrieved2)
      )

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(Some(movie)))
      when(instanceOf[MovieRatingModel]().findByMovieId(movieId)).thenReturn(Future(Seq(movieRating1, movieRating2)))

      val result = await {
        instanceOf[MovieService]().getMovieRatings(movieId)
      }

      result mustBe movieRatings
    }

    "throw MovieNotFoundException when movie doesn't exist" in {
      val movieId = 1

      when(instanceOf[MovieModel]().findById(movieId)).thenReturn(Future(None))

      val exception = the[ApiException] thrownBy await {
        instanceOf[MovieService]().getMovieRatings(movieId)
      }

      exception.statusCode mustBe 404
      exception.errorMessage mustBe "error.movie.not.found"
    }
  }
}
