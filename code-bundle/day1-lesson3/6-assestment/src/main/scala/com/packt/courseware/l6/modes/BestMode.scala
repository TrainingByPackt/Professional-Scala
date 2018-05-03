package com.packt.courseware.l6.modes

import com.packt.courseware.l6.{EffectsProvider, Processed}

case class BestMode(frs: ChatbotMode, snd:ChatbotMode) extends ChatbotMode {

  override def process(line: String, effects: EffectsProvider): Option[Processed] = {
    frs.process(line,effects).map{ frsResult =>
        snd.process(line,effects) match {
          case Some(sndResult) =>
            if (frsResult.relevance >= sndResult.relevance) {
              frsResult.copy(nextMode = BestMode(frsResult.nextMode,snd))
            } else {
              sndResult.copy(nextMode = BestMode(sndResult.nextMode,snd))
            }
          case None => frsResult.copy(nextMode = BestMode(frsResult.nextMode,snd))
        }
    }.orElse{
      snd.process(line,effects).map{ sndResult =>
        sndResult.copy(nextMode = BestMode(sndResult.nextMode,snd))
      }
    }

  }

}
