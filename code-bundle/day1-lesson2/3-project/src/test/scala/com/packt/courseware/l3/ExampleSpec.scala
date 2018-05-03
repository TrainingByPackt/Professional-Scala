package com.packt.courseware.l3

import org.scalatest.FunSuite

class ExampleSpec extends FunSuite {

  test("example test  should pass") {
     assert(1==1)
  }

  test("named parameters") {
    def f(x:Int, y:Int) = x*2 + y

    f(x=1,y=2) // 4
    f(y=1,x=2) // 5
  }

}
