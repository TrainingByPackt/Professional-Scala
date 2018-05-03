package com.packt.courseware.l1

import org.scalatest.FunSuite

class StepTest extends FunSuite {

  test("step of unparded word must be interesting") {
    val r = Chatbot2.step("qqqq")
    assert(! r.timeToBye)
    assert(r.answer == "interesting...")
  }

}
