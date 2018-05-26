package com.packt.courseware.l4

case class CartesianPoint(x:Double, y:Double) extends Point2D {


  def *(v:Double) = new CartesianPoint(x,y)

}
