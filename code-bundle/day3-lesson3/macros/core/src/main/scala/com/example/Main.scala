object Test extends App {

  val x = Macros.uppercase("testing")
  println(x)


  trait Showable[T] { def show(x: T): String }

//  object Showable {
//    implicit def materializeShowable[T]: Showable[T] = macro ...
//  }

  final case class Person(name: String)

  object Person {
    implicit val showable: Showable[Person] = new Showable[Person]{
      def show(x: Person) = s"Person(name=${x.name})"
    }
  }

  val s = implicitly[Showable[Person]]
  println(s.show(Person("Mads")))

}
