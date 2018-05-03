package com.packt.courseware.l3.modes

import java.time.format.DateTimeFormatter

import com.packt.courseware.l3._

object CurrentTime extends ChatbotMode{

  override def process(message: String, effectsProvider: EffectsProvider): LineStepResult =
  {
    if (message=="time") {
      val currentTime = effectsProvider.currentTime
      val message = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))
      Processed(message,this,false)
    } else {
      Failed
    }
  }

}
