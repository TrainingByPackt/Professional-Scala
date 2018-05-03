package com.packt.courseware.l4


import org.scalatest.FunSuite

class PolarPointTest extends FunSuite {

  test("phi = 0 => radius == x") {
    val p = new PolarPoint(0)
    assert(p.radius == 1)
  }

  test("pattern matching") {
    val p = PolarPoint(0,1)
    val r = p match {
      case PolarPoint(_,0) => "zero"
      case x: PolarPoint if (x.radius == 1) => "r=1"
      case v@PolarPoint(x,y) => s"(x=${x},y=${y})"
      case _ => "not polar point"
    }
    assert(r == "r=1")
  }

  test("anonimous class with instance") {
    val p = new Point2D {
      override def x: Double = 1
      override def y: Double = 0
    }
    assert(p.length() == 1)
  }


}
