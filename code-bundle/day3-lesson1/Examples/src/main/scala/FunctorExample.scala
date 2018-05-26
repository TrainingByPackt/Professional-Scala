object FunctorExample extends App {

  def compute[F[_]](fa: F[Int])(implicit f: Functor[F]): F[Int] = {
    val fx = f.map(fa) { _ + 2 }
    f.map(fx) { _ * 2}
  }

  val xs = List(1,2,3)
  val opt = Option(2)
  val either: Either[String, Int] = Right(2)

  println(compute(List(1,2,3)))
  println(compute(Option(2)))
  println(compute(Right(2): Either[String, Int]))

}
