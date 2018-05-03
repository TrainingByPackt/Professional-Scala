package com.packt.courseware.l4

import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val mode = Chatbot4.createInitMode()
    val effects = Chatbot4.effects
    val result = mode("qqq",effects)
    assert(result.isInstanceOf[Processed])
    val r = result.asInstanceOf[Processed]
    assert(r.answer == "interesting...")
  }


}
