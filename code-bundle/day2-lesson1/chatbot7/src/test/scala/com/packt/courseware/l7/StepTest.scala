package com.packt.courseware.l7

import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val mode = Chatbot7.createInitMode()
    val effects = Chatbot7.effects
    val result = mode.process("qqq",effects)
    assert(result.isDefined)
    val r = result.get
    assert(r.answer == "interesting...")
  }


}
