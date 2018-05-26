package com.packt.courseware.l4

import com.packt.courseware.l4.modes.StoreRemindCommand
import org.scalatest.FunSuite

class StoreRemindTest extends FunSuite {

  val effects = Chatbot4.effects

  test("emprty word must not be stored") {
    val process = StoreRemindCommand.empty
    assert(! process.isDefinedAt(("aaa",effects)))
  }

  test("store and then ask") {
    val p0 = StoreRemindCommand.empty
    val p1 = p0(("store AAA AAA-Def",effects)).nextMode
    val p2 = p1("remind AAA",effects)

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
