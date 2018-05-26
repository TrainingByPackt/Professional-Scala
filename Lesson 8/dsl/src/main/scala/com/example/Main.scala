package com.example

import collection.mutable.Stack

/**
  * Example that shows how to use the DSL to define tests and run them.
  */
object Main extends App {

  import Assertion._

  val x = "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)

    (expected(2) from stack.pop()).run()
    (expected(1) from stack.pop()).run()
  }

  val y = "A Stack" should "can be popped when empty" in {
    val stack = new Stack[Option[Int]]
    assert(stack.pop().isEmpty, "You can pop and empty stack.")
  }

  val withoutDSL = TestCase(
    TestDescription("A stack", "pop values in last-in-first-out order"),
    TestResult.wrap({
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      (expected(2) from stack.pop()).run()
      (expected(1) from stack.pop()).run()
    })
  )

  TestRunner.run(x,y)
}
