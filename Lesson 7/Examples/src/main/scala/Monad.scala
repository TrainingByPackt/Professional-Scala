
trait Monad[F[_]] extends Functor[F] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  def pure[A](x: A): F[A]
  def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(f andThen pure)
}

object Monad {

  implicit val optionMonad = new Monad[Option] {
    def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa match {
      case Some(x) => f(x)
      case None => None
    }
    def pure[A](x: A): Option[A] = Some(x)
  }

  import optionMonad._

  def big(i: Int): Option[Int] =
    if (i > 5) Some(i)
    else None

    val y = flatMap(map(big(10))(_ - 5))(big)

  println(y)

}

