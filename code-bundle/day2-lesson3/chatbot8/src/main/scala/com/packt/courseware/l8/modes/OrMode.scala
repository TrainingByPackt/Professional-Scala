package com.packt.courseware.l8.modes

import com.packt.courseware.l8.{EffectsProvider, Processed}

case class OrMode(frs:ChatbotMode, snd:ChatbotMode) extends ChatbotMode {

  override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] = {
    frs.process(line).map(
      p => p.copy(nextMode = OrMode(p.nextMode,snd))
    ) orElse snd.process(line).map(
      p => p.copy(nextMode = OrMode(p.nextMode,frs))
    )
  }

}
