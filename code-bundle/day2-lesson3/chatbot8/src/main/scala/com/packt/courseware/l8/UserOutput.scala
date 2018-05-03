package com.packt.courseware.l8


trait UserOutput {

  def write(message: String): Unit

  def writeln(message: String): Unit = {
    write(message)
    write("\n")
  }

}


object ConsoleOutput extends UserOutput
{

  def write(message: String): Unit = {
    Console.print(message)
  }

}