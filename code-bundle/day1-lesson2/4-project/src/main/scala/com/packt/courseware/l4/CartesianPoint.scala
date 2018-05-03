package com.packt.courseware.l4

case class CartesianPoint(x:Double, y:Double) extends Point2D {

  override def length(): Double = x*x + y*y

}
