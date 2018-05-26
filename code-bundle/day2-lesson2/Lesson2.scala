package professionalscala

object Lesson2 extends App {
  trait IntTree
  case class IntNode(value: Int, left: IntTree, right: IntTree) extends IntTree
  case object IntEmpty extends IntTree

  def insert(value: Int, tree: IntTree): IntTree =
    tree match {
      case IntEmpty => IntNode(value, IntEmpty, IntEmpty)
      case IntNode(currentValue, left, right) =>
        if (value < currentValue)
          IntNode(currentValue, insert(value, left), right)
        else
          IntNode(currentValue, left, insert(value, right))
    }

  def search(value: Int, tree: IntTree): Boolean =
    tree match {
      case IntEmpty => false
      case IntNode(currentValue, left, right) =>
        value == currentValue ||
          (value < currentValue && search(value, left)) ||
          (value >= currentValue && search(value, right))
    }

  trait Tree[+A]
  case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]
  case object Empty extends Tree[Nothing]

  def insertG[A](value: A, tree: Tree[A], comp: (A, A) => Boolean): Tree[A] =
    tree match {
      case Empty => Node(value, Empty, Empty)
      case Node(currentValue, left, right) =>
        if (comp(value, currentValue))
          Node(currentValue, insertG(value, left, comp), right)
        else
          Node(currentValue, left, insertG(value, right, comp))
    }

  def toList[A](tree: Tree[A]): List[A] =
    tree match {
      case Empty => Nil
      case Node(value, left, right) => toList(left) ++ (value :: toList(right))
    }

  val rand = (0 until 10).map(_ => scala.util.Random.nextInt(100))
  val result = rand.foldRight(Empty: Tree[Int])(insertG(_, _, (v1: Int, v2: Int) => v1 < v2))

  def merge[A, B <: A, C <: A, D >: A](tree1: Tree[B], tree2: Tree[C], comp: (D, D) => Boolean): Tree[D] =
    toList(tree2).foldRight(tree1: Tree[D])(insertG(_, _, comp))

  trait Tool {
    def weight: Long
    def price: Long
  }

  trait HandTool extends Tool
  trait PowerTool extends Tool
  case class Hammer(weight: Long, price: Long) extends HandTool
  case class Screwdriver(weight: Long, price: Long) extends HandTool
  case class Driller(weight: Long, price: Long) extends PowerTool

  def weightCompare[A <: Tool] = (v1: A, v2: A) => v1.weight < v2.weight
  def priceCompare[A <: Tool] = (v1: A, v2: A) => v1.price < v2.price

  val hammers = (0 until 10).foldLeft(Empty: Tree[Hammer]) { case (t, _) =>
    insertG(Hammer(scala.util.Random.nextLong(), scala.util.Random.nextLong()), t, weightCompare)
  }
  val screwdrivers = (0 until 10).foldLeft(Empty: Tree[Screwdriver]) { case (t, _) =>
    insertG(Screwdriver(scala.util.Random.nextLong(), scala.util.Random.nextLong()), t, weightCompare)
  }

  println(toList(merge(hammers, screwdrivers, weightCompare)))
}
