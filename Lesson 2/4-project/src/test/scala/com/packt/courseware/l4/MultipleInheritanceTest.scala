package com.packt.courseware.l4

import org.scalatest.FunSuite

object MultipleInheritanceTest {

  trait A {
    def f = "f.A"
  }

  trait B {
    def f = "f.B"

    def g = "g.B"
  }


  trait C extends A with B {
    override def f = "f.C" //  won't compile without override.
  }

  trait B1 extends A {
    def g = "g.B1"
  }

  trait D1 extends B1 with C
  {
    override def g = super.g
  }

  trait D2 extends C with B1
  {
    override def g = super.g
  }


  //
  trait B2 extends A
  {
    abstract override def f():String = { super.f+" in B2" }
  }

  trait B3 extends A
  {
    override def f():String = { super.f + " in B3"}
  }





}

class MultipleInheritanceTest extends FunSuite {

  import com.packt.courseware.l4.MultipleInheritanceTest._



  test("C.f") {
    val x = new C{}
    assert(x.f == "f.C")
  }

  test("D1.g") {
    val x = new D1{}
    assert(x.g == "g.B")
  }

  test("D2.g") {
    val x = new D2{}
    assert(x.g == "g.B1")
  }


}
