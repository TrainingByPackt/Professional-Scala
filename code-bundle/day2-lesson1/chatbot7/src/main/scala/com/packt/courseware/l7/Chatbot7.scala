package com.packt.courseware.l7

import scala.io.StdIn

object Chatbot7 {

  val effects = DefaultEffects

  def main(args: Array[String]): Unit = {

    val name = StdIn.readLine("Hi! What is your name? ")
    println(s" $name, tell me something interesting, say 'bye' to end the talk")

    val mode = createInitMode()
    var c = Processed("", mode, false)
    while (!c.endOfDialog) {
      val line = effects.input.read()
      c = c.nextMode.process(line, effects).getOrElse(
        Processed("Sorry, can't understand you", c.nextMode, false)
      )
      effects.output.writeln(c.answer)
    }

  }

  import modes._

  def createInitMode() = (StoreRemindCommand.empty or
    TodoList.empty or
    bye or
    CurrentDate or currentTime
    ) otherwise interestingIgnore
}
