package professionalscala

import professionalscala.Implicits.RichInt

sealed trait JsValue
case class JsObject(fields: Map[String, JsValue]) extends JsValue
case class JsArray(elements: Vector[JsValue]) extends JsValue
case class JsString(value: String) extends JsValue
case class JsNumber(value: BigDecimal) extends JsValue

sealed trait JsBoolean extends JsValue
case object JsTrue extends JsBoolean
case object JsFalse extends JsBoolean

case object JsNull extends JsValue

trait JsonWritable {
  def toJson: JsValue
}

class IntJsonWritable(value: Int) extends JsonWritable {
  def toJson = JsNumber(value)
}

class StringJsonWritable(value: String) extends JsonWritable {
  def toJson: JsValue = JsString(value)
}

class TraversableJsonWritable(value: Traversable[JsValue]) extends JsonWritable {
  def toJson: JsValue = JsArray(value.toVector)
}

class MapJsonWritable(value: Map[String, JsValue]) extends JsonWritable {
  def toJson: JsValue = JsObject(value)
}

object Implicits {
  class RichInt(val value: Int) extends AnyVal {
    def square: Int = value * value
    def plus(other: Int) = value + other
  }
}

trait JsonWriter[A] { def write(value: A): JsValue }

object JsonWriter {
  implicit object IntJsonWriter extends JsonWriter[Int] {
    def write(value: Int): JsValue = JsNumber(value)
  }

  implicit object StringJsonWriter extends JsonWriter[String] {
    def write(value: String): JsValue = JsString(value)
  }

  implicit object BooleanJsonWriter extends JsonWriter[Boolean] {
    def write(value: Boolean): JsValue = value match {
      case true => JsTrue
      case false => JsFalse
    }
  }

  implicit def listJsonWriter[A](implicit jw: JsonWriter[A]): JsonWriter[List[A]] = new JsonWriter[List[A]] {
    def write(l: List[A]): JsValue =
      JsArray(l.map(jw.write).toVector)
  }

  implicit def setJsonWriter[A](implicit jw: JsonWriter[A]): JsonWriter[Set[A]] = new JsonWriter[Set[A]] {
    def write(s: Set[A]): JsValue =
      JsArray(s.map(jw.write).toVector)
  }

  implicit def mapJsonWriter[A](implicit jw: JsonWriter[A]): JsonWriter[Map[String, A]] = new JsonWriter[Map[String, A]] {
    def write(m: Map[String, A]): JsValue =
      JsObject(m.mapValues(jw.write))
  }
}

object Lesson3 extends App {
  // Custom implementation of JsonWriter[String]
  // overriding the default one.
  def toJson[A](value: A)(implicit jw: JsonWriter[A]): JsValue =
    jw.write(value)

  //println(toJson(List(1, 2, 3)))
  println(toJson(Map("a" -> Map("b" -> List(1, 2, 3)), "c" -> Map("d" -> List(4, 5, 6)))))

  // Does not compile.
  //def ordered[A](l: List[A]) = l.sorted

  // Compiles, since we now have an implicit
  // Ordering defined for A
  def ordered[A: Ordering](l: List[A]) = l.sorted

  println(toJson("world!"))
  // Returns JsString("Hello world!")

  //println(ordered(List(4, 1, 2)))
  //import Implicits._

  implicit def foo(i: Int): RichInt = new RichInt(i)

  implicitly[Ordering[Int]]

  2.square
  // Returns 4
  2.plus(5)
  // Returns 7

  object Prompt {
    implicit val defaultPrompt = Prompt("action")
  }

  case class Prompt(value: String)
  def message(msg: String)(implicit prompt: Prompt) = {
    println(msg)
    println(s"${prompt.value}>")
  }

  message("Welcome!")(Prompt("action"))

  message("Welcome!")
  message("What do you want to do next?")

  implicit def intToIterable(i: Int): Traversable[Int] = new Traversable[Int] {
    override def foreach[U](f: Int => U): Unit = {
      var value = i
      var l = List.empty[Int]
      do {
        l = value % 10 :: l
        value /= 10
      } while (value != 0)
      l.foreach(f)
    }
  }

  def orderedSeq[A: Ordering](t: Traversable[A]) = t.toSeq.sorted

  println(orderedSeq(472).toList)


  object Defaults1 {
    implicit val n: Int = 5
  }
  object Defaults2 {
    implicit val n: Int = 3
  }
  implicit val n: Int = 4
  def add(x: Int)(implicit y: Int) = x + y
  add(5)
  println(add(5))
  // Takes n from the current scope, res: Int = 10

  class A(val n: Int)
  object A {
    implicit val ord = new Ordering[A] {
      def compare(x: A, y: A): Int = x.n - y.n
    }
  }

  trait Adder[A] {
    def add(a: A, b: A): A
  }

  case class Foo(i: Int)
  object Foo {
    implicit val adder1: Adder[Foo] = new Adder[Foo] {
      def add(a: Foo, b: Foo) = Foo(a.i + b.i)
    }
  }
  implicit val adder2: Adder[Foo] = new Adder[Foo] {
    def add(a: Foo, b: Foo) = Foo(a.i * 2 + b.i * 2)
  }

  def add[A](a: A, b: A)(implicit adder: Adder[A]): A = adder.add(a, b)
  add(Foo(1), Foo(2))
}
