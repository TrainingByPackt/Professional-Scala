package com.packt.courseware.l3

sealed trait LineStepResult

case class Processed(answer:String, nextMode: ChatbotMode, endOfDialog:Boolean) extends LineStepResult
case object Failed extends LineStepResult
