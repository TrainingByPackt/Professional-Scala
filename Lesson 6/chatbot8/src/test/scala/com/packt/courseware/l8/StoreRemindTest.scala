package com.packt.courseware.l8

import com.packt.courseware.l8.modes.StoreRemindCommand
import org.scalatest.FunSuite

class StoreRemindTest extends FunSuite {

  implicit val effects = Chatbot7.effects

  test("emprty word must not be stored") {
    val p = StoreRemindCommand.empty
    val r = p.process("aaa")
    assert(r.isEmpty)
  }

  test("store and then ask") {
    val p0 = StoreRemindCommand.empty
    val p1 = p0.process("store AAA AAA-Def").get.nextMode
    val p2 = p1.process("remind AAA").get

    assert(p2.answer == "AAA-Def")
  }

  test("store parser") {
    val p = "store AAA AAA-Def"
    val rexp = raw"store ([^\W]+) (.*)".r;
    val r = rexp.unapplySeq(p)
    assert(r.isDefined)
    assert(r.get(0)=="AAA")
    assert(r.get(1)=="AAA-Def")
  }

}
