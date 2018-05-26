package com.example

case class Assertion[A](expected: Expected[A], value: () => A) {
  def run(): Unit = {
    val result = value()
    assert(expected.expected == result, s"Failed asserting that ${expected.expected} == $result")
  }
}

case class Expected[A](expected: A) {
  def from(expression: => A): Assertion[A] = Assertion(
    this,
    () => expression
  )
}

object Assertion {

  def expected[A](x: A): Expected[A] = Expected(x)

}
