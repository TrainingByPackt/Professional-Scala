package com.packt.courseware.l4.modes

import com.packt.courseware.l4.{EffectsProvider, Processed}

case class OtherwiseMode(frs: ChatbotMode, snd: ChatbotMode) extends ChatbotMode {

  override def process(line: String, effects: EffectsProvider): Option[Processed] =
    frs.process(line,effects).map( p =>
      p.copy(nextMode = p.nextMode otherwise snd)
    ) orElse snd.process(line,effects).map( p =>
      p.copy(nextMode = frs otherwise p.nextMode)
    )

}
