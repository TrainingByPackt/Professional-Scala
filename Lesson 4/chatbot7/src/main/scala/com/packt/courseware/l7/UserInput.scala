package com.packt.courseware.l7

import scala.io.StdIn

trait UserInput {
  def read(): String
}

object ConsoleInput extends UserInput {
  def read(): String = StdIn.readLine("> ")
}
