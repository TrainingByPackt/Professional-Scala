package com.packt.courseware.l1

import java.time.LocalTime
import java.time.format.DateTimeFormatter

import scala.io.StdIn

case class LineProcessResult(answer:String,timeToBye:Boolean)

object Chatbot2 {

  def main(args: Array[String]): Unit = {
    val name = StdIn.readLine("Hi! What is your name? ")
    println(s" $name, tell me something interesting, say 'bye' to end the talk")

    var c = LineProcessResult("",false)
    while(!c.timeToBye){
      c = step(StdIn.readLine(">"))
      println(c.answer)
    }

  }

  def step(input:String): LineProcessResult = {
    input match {
      case "bye" => LineProcessResult("ok, bye", true)
      case "time" => LineProcessResult(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),false)
      case _ => LineProcessResult("interesting...", false)
    }
  }

}
