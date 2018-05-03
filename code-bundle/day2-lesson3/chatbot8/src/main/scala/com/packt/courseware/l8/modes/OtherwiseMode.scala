package com.packt.courseware.l8.modes

import com.packt.courseware.l8.{EffectsProvider, Processed}

case class OtherwiseMode(frs: ChatbotMode, snd: ChatbotMode) extends ChatbotMode {

  override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] =
    frs.process(line).map( p =>
      p.copy(nextMode = p.nextMode otherwise snd)
    ) orElse snd.process(line).map( p =>
      p.copy(nextMode = frs otherwise p.nextMode)
    )

}
