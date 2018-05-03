package com.packt.courseware.l8.modes

import com.packt.courseware.l8.{EffectsProvider, Processed}

import scala.collection.immutable.Queue

case class TodoList(todos:Vector[String]) extends ChatbotMode {

  import TodoList._

  override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] =
  {
    line match {
      case ListPattern(_) => Some(doList())
      case NewPattern(text) => Some(doNew(text))
      case DonePattern(text) => doDone(text)
      case _ => None
    }
  }

  def taskListAnswer(): String =
  {
    todos.zipWithIndex.map{ case (name,number) => s"${number} : ${name}"}.mkString("\n")
  }

  def doList():Processed =
  {
    val answer = taskListAnswer()
    Processed(answer,this)
  }

  def doNew(text:String):Processed = {
    Processed("ok, added",TodoList( todos :+ text ))
  }

  def doDone(text:String):Option[Processed] = {
    val answerAsNumber = tryDoneAsNumber(text)
    val answerAsText = tryDoneAsText(text)

    tryDoneAsNumber(text) match {
      case rpn@Some(pn) => tryDoneAsText(text) match {
                         case Some(pt) => // ambiguous, can't choose.
                           Some(Processed("err:both matching as term and as text are possible.",this))
                         case None => rpn
                       }
      case None => tryDoneAsText(text)
    }
  }

  def tryDoneAsNumber(text:String): Option[Processed] = {
    text match {
      case Digits(x) =>
        try {
          val n = x.toInt
          if (n < todos.length) {
            val newTodos = todos.patch(n,Vector.empty,1)
            System.err.println(s"drop by number ${n} todos=${todos}, newTodos=${newTodos}")
            val nextStep = TodoList(newTodos)
            Some(Processed(s"ok, tasks left:\n${nextStep.taskListAnswer()}",nextStep))
          } else {
            None
          }
        }catch{
          case ex: NumberFormatException =>
            None
        }
      case _ => None
    }
  }

  def tryDoneAsText(text: String): Option[Processed] = {
    val selectedIndex = todos.indexWhere(_.indexOf(text) >= 0)
    if (selectedIndex < 0) {
      None
    } else {
      // check, is our selection is unique
      val nextIndex = todos.indexWhere(_.indexOf(text)>= 0, selectedIndex+1)
      if (nextIndex < 0) {
        // unique
        val deleted = todos(selectedIndex)
        val newTodos = todos.patch(selectedIndex,Vector.empty,1)
        Some(Processed(s"ok, deleted ${deleted}",TodoList(newTodos)))
      } else {
        None
      }
    }
  }


}


case object TodoList {

  val ListPattern = raw"(todo list)".r
  val NewPattern = raw"todo new ([^\W]+.*)".r
  val DonePattern = raw"todo done ([^\W]+.*)".r
  val ObsoletePattern = raw"todo done ([^\W]+.*)".r

  val Digits = raw"([0-9]+)".r

  val empty = TodoList(Vector())

}
