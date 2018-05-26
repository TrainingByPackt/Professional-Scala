package com.packt.courseware.l7

import com.packt.courseware.l7.modes.{BestMode, ChatbotMode, StoreRemindCommand}
import org.scalatest.FunSuite

class BestTest extends FunSuite {

  val effects = Chatbot7.effects


  test("when first relevance in 0.5 and second is 0.6 - choose second") {
    lazy val frs: ChatbotMode = ChatbotMode.partialFunction{ case _ => Processed("0.5",frs,false,0.5)}
    lazy val snd: ChatbotMode = ChatbotMode.partialFunction{ case _ => Processed("0.6",snd,false,0.6)}
    val best = BestMode(frs,snd)
    val r = best.process("aaa",effects)
    assert(r.isDefined)
    assert(r.get.answer == "0.6")
  }

  test("when first relevance in 0.6 and second is 0.5 - choose first") {
    lazy val frs: ChatbotMode = ChatbotMode.partialFunction{ case _ => Processed("0.6",frs,false,0.6)}
    lazy val snd: ChatbotMode = ChatbotMode.partialFunction{ case _ => Processed("0.5",snd,false,0.5)}
    val best = BestMode(frs,snd)
    val r = best.process("aaa",effects)
    assert(r.isDefined)
    assert(r.get.answer == "0.6")
  }

  test("when first relevance in 0.1 and second is not defined - choose first") {
    lazy val frs: ChatbotMode = ChatbotMode.partialFunction{ case _ => Processed("0.1",frs,false,0.1)}
    val snd: ChatbotMode = ChatbotMode.partialFunction{ PartialFunction.empty }
    val best = BestMode(frs,snd)
    val r = best.process("aaa",effects)
    assert(r.isDefined)
    assert(r.get.answer == "0.1")
  }


}
