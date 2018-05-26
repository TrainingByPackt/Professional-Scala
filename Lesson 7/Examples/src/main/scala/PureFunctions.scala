object PureFunctions extends App {

  case class Person(var name: String, var age: Int)

  def birthday(p: Person) = p.age += 1

  def getName(p: Person) = {
    println(s"Getting the name of ${p.name}")
    p.name
  }

  def rename(p: Person, name: String) = Person(name, p.age)

  val p = Person("Jack", 41)
  birthday(p)
  println(p)
  birthday(p)
  println(p)

  val n1 = getName(p)
  val n2 = getName(p)

  println(n1)
  println(n2)

  val r1 = rename(p, "John")
  val r2 = rename(p, "John")

  println(r1)
  println(r2)

}
