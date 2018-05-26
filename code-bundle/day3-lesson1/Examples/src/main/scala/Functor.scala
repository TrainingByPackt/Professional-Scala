trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {

  implicit val optionFunctor = new Functor[Option] {
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
      case None => None
      case Some(x) => Some(f(x))
    }
  }

  implicit val listFunctor = new Functor[List] {
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa match {
      case Nil => Nil
      case x :: xs => f(x) :: map(xs)(f) // warning: not tail-recursive.
    }
  }

  implicit def eitherFunctor[E]: Functor[({type L[A] = Either[E, A]})#L] =
    new Functor[({type L[A] = Either[E, A]})#L] {
      def map[A, B](fa: Either[E, A])(f: A => B): Either[E, B] = fa match {
        case Left(l) => Left(l)
        case Right(x) => Right(f(x))
      }
    }
}

