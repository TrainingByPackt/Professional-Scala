package com.packt.courseware.l6.modes

import com.packt.courseware.l6.{EffectsProvider, Processed}

case class OrMode(frs:ChatbotMode, snd:ChatbotMode) extends ChatbotMode {

  override def process(line: String, effects: EffectsProvider): Option[Processed] = {
    frs.process(line,effects).map(
      p => p.copy(nextMode = OrMode(p.nextMode,snd))
    ) orElse snd.process(line,effects).map(
      p => p.copy(nextMode = OrMode(p.nextMode,frs))
    )
  }

}
