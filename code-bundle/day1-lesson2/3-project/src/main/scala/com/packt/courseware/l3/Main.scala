package com.packt.courseware.l3

import java.time.LocalTime
import java.time.format.DateTimeFormatter

import com.packt.courseware.l3.modes.{Bye, CurrentDate, CurrentTime, InterestingIgnore}

import scala.io.StdIn


object Chatbot3 {

  val effects = DefaultEffects

  def main(args: Array[String]): Unit = {

    val name = StdIn.readLine("Hi! What is your name? ")
    println(s" $name, tell me something interesting, say 'bye' to end the talk")

    var mode = createInitMode()
    var c = Processed("",mode,false)
    while(!c.endOfDialog){
      c = c.nextMode.process(effects.input.read(),effects) match {
        case next@Processed(_,_,_) => next
        case Failed => Processed("Sorry, can't understand you",c.nextMode,false)
      }
      effects.output.writeln(c.answer)
    }

  }

   def createInitMode() = (Bye or CurrentDate or CurrentTime) otherwise InterestingIgnore



}
