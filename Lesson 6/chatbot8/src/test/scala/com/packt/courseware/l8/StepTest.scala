package com.packt.courseware.l8

import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val mode = Chatbot7.createInitMode()
    implicit val effects = Chatbot7.effects
    val result = mode.process("qqq")
    assert(result.isDefined)
    val r = result.get
    assert(r.answer == "interesting...")
  }


}
