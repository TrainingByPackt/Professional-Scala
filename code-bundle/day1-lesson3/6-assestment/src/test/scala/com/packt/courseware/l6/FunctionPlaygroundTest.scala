package com.packt.courseware.l6

import org.scalatest.FunSuite

class FunctionPlaygroundTest extends FunSuite {

  def twice(f: Int => Int): Int => Int = x => f(f(x))

  //   val twice(f:Int=>Int):Int=>Int = x => f(f(x))
  object  Twice extends Function1[Function1[Int,Int],Function1[Int,Int]] {

    def apply(f: Function1[Int,Int]): Function1[Int,Int] =
      new Function1[Int,Int] {
        def apply(x:Int):Int =   f.apply(f.apply(x))
      }

  }

  def fix1(f:(Int,Int)=>Int,x: Int): Int => Int = y => f(x,y)


  test("twice must do twice") {
    val incr2 = (x:Int) => x+2
    val incr4 = twice(incr2)
    assert(incr4(2)==6)

    assert(twice(x=>x+2)(5)==9)
  }

  test("Twice must do twice") {
    val incr2 = (x:Int) => x+2
    val incr4 = Twice(incr2)
    assert(incr4(2)==6)

    assert(Twice(x=>x+2)(5)==9)
  }

  test("fix test") {
    val g = fix1((x,y)=>x+y,3)
    assert(g(2)==5)
  }



}