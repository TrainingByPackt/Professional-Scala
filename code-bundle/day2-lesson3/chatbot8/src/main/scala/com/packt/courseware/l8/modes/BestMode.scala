package com.packt.courseware.l8.modes

import com.packt.courseware.l8.{EffectsProvider, Processed}

case class BestMode(frs: ChatbotMode, snd:ChatbotMode) extends ChatbotMode {

  override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] = {
    frs.process(line).map{ frsResult =>
        snd.process(line) match {
          case Some(sndResult) =>
            if (frsResult.relevance >= sndResult.relevance) {
              frsResult.copy(nextMode = BestMode(frsResult.nextMode,snd))
            } else {
              sndResult.copy(nextMode = BestMode(sndResult.nextMode,snd))
            }
          case None => frsResult.copy(nextMode = BestMode(frsResult.nextMode,snd))
        }
    }.orElse{
      snd.process(line).map{ sndResult =>
        sndResult.copy(nextMode = BestMode(sndResult.nextMode,snd))
      }
    }

  }

}
