package com.packt.courseware.l7

import com.packt.courseware.l7.modes.{ChatbotMode, TodoList}
import org.scalatest.FunSuite

class TodoListTest extends FunSuite {

  val effects = Chatbot7.effects

  test("adding to emoty todolist") {
    val mode = TodoList.empty
    val message = "AAA sdd AAAAAA"
    val p1 = mode.process(s"todo new ${message}", effects)
    assert(p1.isDefined)
    assert(p1.get.answer.startsWith("ok"))
    val p2 = p1.get.nextMode.process("todo list", effects)
    assert(p2.isDefined)
    assert(p2.get.answer.startsWith("0"))
    assert(p2.get.answer.contains(message))
  }

  test("adding and remove items") {
    var mode: ChatbotMode = TodoList.empty
    val messages = Vector("zero", "first", "second", "third", "fourth", "fifths", "six-s", "sevent0s", "eights", "nine-s")
    for (i <- 0 until 10) {
      val p = mode.process(s"todo new ${messages(i)}", effects)
      mode = p.get.nextMode
    }
    val p1 = mode.process("todo list", effects)
    assert(p1.isDefined)
    mode = p1.get.nextMode
    assert(p1.get.answer.contains("0"))
    assert(p1.get.answer.contains("9"))
    val p2 = mode.process("todo done 5", effects)
    assert(p2.isDefined)
    assert(p2.get.answer.startsWith("ok"))
    mode = p2.get.nextMode
    val p3 = mode.process("todo list", effects)
    assert(!p3.get.answer.contains("fifths"))
  }

  test("ambiguos") {
    var mode: ChatbotMode = TodoList.empty
    for (i <- 0 until 10) {
      val p = mode.process(s"todo new $i", effects)
      mode = p.get.nextMode
    }
    val p2 = mode.process("todo done 5", effects)
    assert(p2.isDefined)
    assert(!p2.get.answer.startsWith("ok"))
  }


}
