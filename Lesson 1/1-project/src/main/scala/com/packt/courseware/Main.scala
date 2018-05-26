package com.packt.courseware

import scala.io.StdIn

object Chatbot1 {

  def main(args: Array[String]): Unit = {
    val name = StdIn.readLine("Hi! What is your name?")
    println(s" $name, tell me something interesting, say 'bay' to end the talk")
    var timeToBye = false
    while (!timeToBye)
      timeToBye = StdIn.readLine(">") match {
        case "bye" => println("ok, bye")
          true
        case _ => println("interesting...")
          false
      }
  }

}
