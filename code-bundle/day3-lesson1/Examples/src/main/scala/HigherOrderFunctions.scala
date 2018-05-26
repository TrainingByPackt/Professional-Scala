
object HigherOrderFunctions extends App {

  def negate[A](f: A => Boolean): A => Boolean = (a: A) => !f(a)

  def sndWhere[A](xs: List[A])(pred: A => Boolean): Option[A] = {
    val noMatch = negate(pred(_))
    xs
      .dropWhile(noMatch)
      .drop(1)
      .dropWhile(noMatch)
      .headOption
  }


  println(sndWhere(List(1,3,2,4,4))(_ > 2))
  println(sndWhere(List(1,3,2,4,4))(_ > 10))

}
