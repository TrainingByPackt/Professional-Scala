package com.packt.courseware.l4

import org.scalatest.FunSuite

sealed trait BinaryTree

object BinaryTree {

  case class Leaf(value: String) extends BinaryTree

  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

}

class PatternMatchingTest extends FunSuite {



}
