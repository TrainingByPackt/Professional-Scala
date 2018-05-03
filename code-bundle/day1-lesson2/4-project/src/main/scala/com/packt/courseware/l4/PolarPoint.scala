package com.packt.courseware.l4

import math._

case class PolarPoint(val phi:Double, val radius:Double) extends Point2D
{
  require(phi >= - Pi && phi < Pi )
  require(radius >= 0)

  def this(phi:Double) = this(phi,1.0)

  override def length = radius

  def x: Double = radius*cos(phi)
  def y: Double = radius*sin(phi)

  def * (x:Double) = PolarPoint(phi,radius*x)

}

object PolarPoint
{

  def apply(phi:Double, r:Double) = new PolarPoint(phi,r)

  def test: Unit = {
    val p1 = PolarPoint(Pi,1)
    val p2 = p1.copy(phi=1)
  }
}