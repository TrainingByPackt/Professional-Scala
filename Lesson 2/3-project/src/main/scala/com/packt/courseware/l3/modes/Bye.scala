package com.packt.courseware.l3.modes

import com.packt.courseware.l3._

object Bye extends ChatbotMode {

  override def process(message: String, effects: EffectsProvider): LineStepResult =
    if (message=="bye") {
      Processed("bye",this,true)
    } else Failed

}
