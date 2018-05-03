package com.packt.courseware.l4

import org.scalatest.FunSuite

trait A
{
  def f():String = "A.f"
}

trait B
{
  def f():String = "B.f"
}

class C extends A with B
{
  override def f():String = "C.f"
}

class Example extends FunSuite {

  test("example test  should pass") {
     assert(1==1)
  }

}
