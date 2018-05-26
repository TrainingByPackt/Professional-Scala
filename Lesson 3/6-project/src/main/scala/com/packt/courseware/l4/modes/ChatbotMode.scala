package com.packt.courseware.l4.modes

import com.packt.courseware.l4.{EffectsProvider, Processed}


trait ChatbotMode {

  def process(line:String,effects:EffectsProvider):Option[Processed]

  def or(other: ChatbotMode) = OrMode(this,other)

  def otherwise(other: ChatbotMode) = OtherwiseMode(this,other)

}

object ChatbotMode
{

  def partialFunction(f:PartialFunction[String,Processed]): ChatbotMode =
    { (line,effects) => f.lift(line) }

}