package com.packt.courseware.l7.modes

import com.packt.courseware.l7.{EffectsProvider, Processed}

trait ChatbotMode {
  def process(line: String, effects: EffectsProvider): Option[Processed]

  def or(other: ChatbotMode) = OrMode(this, other)

  def otherwise(other: ChatbotMode) = OtherwiseMode(this, other)

  def best(other: ChatbotMode) = BestMode(this, other)
}

object ChatbotMode {
  def partialFunction(f: PartialFunction[String, Processed]): ChatbotMode = { (line, effects) => f.lift(line) }
}
