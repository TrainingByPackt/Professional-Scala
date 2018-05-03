package com.packt.courseware.l3.modes

import java.text.{DateFormat, SimpleDateFormat}
import java.time.format.DateTimeFormatter

import com.packt.courseware.l3._


object CurrentDate extends ChatbotMode {

  override def process(message: String, effectsProvider: EffectsProvider): LineStepResult =
    if (message == "date") {
      val currentDate = effectsProvider.currentDate()
      val message = currentDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
      Processed(message,this,false)
    } else Failed

}
