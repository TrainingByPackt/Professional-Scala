package com.packt.courseware.l8.modes

import com.packt.courseware.l8.{EffectsProvider, Processed}


trait ChatbotMode {

  def process(line:String)(implicit effects:EffectsProvider):Option[Processed]

  def or(other: ChatbotMode) = OrMode(this,other)

  def otherwise(other: ChatbotMode) = OtherwiseMode(this,other)

  def best(other: ChatbotMode) = BestMode(this,other)

}

object ChatbotMode
{

  def partialFunction(f:PartialFunction[String,Processed]): ChatbotMode =
    new ChatbotMode {
      override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] =
        f.lift(line)
    }

  def function(f: (String, EffectsProvider) => Option[Processed]): ChatbotMode =
    new ChatbotMode {
      override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] =
        f(line,effects)
    }

}