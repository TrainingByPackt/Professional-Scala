package com.packt.courseware.l4

import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val mode = Chatbot4.createInitMode()
    val effects = Chatbot4.effects
    val result = mode.process("qqq",effects)
    assert(result.isDefined)
    val r = result.get
    assert(r.answer == "interesting...")
  }


}
