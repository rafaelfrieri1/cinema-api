import scala.util.Random

trait Randoms {

  private lazy val random = new Random()

  def nextRandomInt(): Int = {
    random.nextInt()
  }

  def nextRandomString(length: Int = 8): String = {
    random.nextString(length)
  }

  def nextRandomBigDecimal(): BigDecimal = {
    BigDecimal.valueOf(random.nextLong())
  }
  
}
