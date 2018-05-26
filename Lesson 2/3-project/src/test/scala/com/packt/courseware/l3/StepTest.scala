package com.packt.courseware.l3


import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val mode = Chatbot3.createInitMode()
    val effects = Chatbot3.effects
    val result = mode.process("qqq",effects)
    assert(result.isInstanceOf[Processed])
    val r = result.asInstanceOf[Processed]
    assert(r.answer == "interesting...")
  }


}
