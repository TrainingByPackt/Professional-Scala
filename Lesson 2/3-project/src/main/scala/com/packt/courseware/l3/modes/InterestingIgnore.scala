package com.packt.courseware.l3.modes

import com.packt.courseware.l3.{ChatbotMode, EffectsProvider, LineStepResult, Processed}

object InterestingIgnore extends ChatbotMode {

  override def process(message: String, effects: EffectsProvider): LineStepResult =
    Processed("interesting...",this,false)
}
