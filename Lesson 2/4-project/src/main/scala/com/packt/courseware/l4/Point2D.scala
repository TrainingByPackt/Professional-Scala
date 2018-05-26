package com.packt.courseware.l4

trait Point2D {

  def x: Double
  def y: Double

  def length():Double = x*x + y*y

}
