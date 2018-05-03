package com.packt.courseware.d2l1


object Lesson1 extends App {
  // def evenInts(l: List[Int]): List[Int] = {
  //   if (l.isEmpty) l
  //   else if (l.head % 2 == 0) l.head :: evenInts(l.tail)
  //   else evenInts(l.tail)
  // }

  def evenInts(l: List[Int]): List[Int] = l match {
    case h :: t if h % 2 == 0 => h :: evenInts(t)
    case _ :: t => evenInts(t)
    case Nil => Nil
  }

  lazy val fibStream: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibStream.zip(fibStream.tail).map { n => n._1 + n._2 }
  lazy val fibIterator = new Iterator[BigInt] {
    var v1 = 0
    var v2 = 1
    val hasNext = true
    def next = {
      val res = v1
      v1 = v2
      v2 = res + v1
      res
    }
  }

  // Does not avoid already visited nodes
  def path(from: Int, to: Int, graph: Map[Int, List[Int]]): List[Int] = {
    def paths(current: Int): Stream[List[Int]] = {
      def bfs(current: Stream[List[Int]]): Stream[List[Int]] = {
        if (current.isEmpty) current
        else current.head #:: bfs(current.tail #::: graph(current.head.head).map(_ :: current.head).toStream)
      }

      bfs(Stream(List(current)))
    }

    paths(from).dropWhile(_.head != to).head.reverse
  }

  type HanoiState = (List[Int], List[Int], List[Int])

  def nextHanoi(current: HanoiState): List[HanoiState] = {
    def setPile(state: HanoiState, i: Int, newPile: List[Int]): HanoiState = i match {
      case 1 => state.copy(_1 = newPile)
      case 2 => state.copy(_2 = newPile)
      case 3 => state.copy(_3 = newPile)
    }

    def pile(i: Int): List[Int] = i match {
      case 1 => current._1
      case 2 => current._2
      case 3 => current._3
    }

    def valid(from: Int, to: Int): Boolean =
      pile(from).nonEmpty && (pile(to).isEmpty || pile(from).head < pile(to).head)

    (for {
      from <- 1 to 3
      to <- 1 to 3
      if valid(from, to)
    } yield {
      val p1 = pile(from)
      val p2 = pile(to)
      setPile(setPile(current, from, p1.tail), to, p1.head :: p2)
    }).toList
  }

  def genericPath[A](from: A, to: A, nextStates: A => List[A]): List[A] = {
    def paths(current: A): Stream[List[A]] = {
      def bfs(current: Stream[List[A]], visited: Set[A]): Stream[List[A]] = {
        if (current.isEmpty) current
        else current.head #:: {
          val next = nextStates(current.head.head).filter(n => !visited.contains(n))
          bfs(current.tail #::: next.map(_ :: current.head).toStream, visited ++ next.toSet)
        }
      }

      bfs(Stream(List(current)), Set(current))
    }

    paths(from).dropWhile(_.head != to).head.reverse
  }

  val fullPile = List(1, 2, 3, 4, 5, 6, 7, 8)
  val start = (fullPile, Nil, Nil)
  val end = (Nil, Nil, fullPile)
  println(genericPath(start, end, nextHanoi).size)
}

