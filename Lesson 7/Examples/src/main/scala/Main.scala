// import cats.Monoid

trait Monoid[A] {
  def combine(x: A, y: A): A
  def empty: A
}


trait Applicative[F[_]] extends Functor[F] {
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  def pure[A](a: A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = ap(pure(f))(fa)
}

object Monoid {

  implicit val stringMonoid = new Monoid[String] {
    def combine(x: String, y: String): String = x + y
    def empty: String = ""
  }

  implicit val intMonoid = new Monoid[Int] {
    def combine(x: Int, y: Int): Int = x + y
    def empty: Int = 0
  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    def combine(x: List[A], y: List[A]): List[A] = x ++ y
    def empty: List[A] = Nil
  }

}

object Main extends App {

  import Monoid._
  import Functor._

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A = {
    xs.foldLeft(m.empty)(m.combine)
  }

  println(
    stringMonoid.combine("Hi", " There")
  )

  println(sum(List("Monoids", " ", "are", " ", "cool")))

  println(sum(List(1,2,3)))

  println(sum(List(
    List(1,2),
    List(3,4)
  )))

  println(listFunctor.map(List(1,2,3))(_ * 2))

}
