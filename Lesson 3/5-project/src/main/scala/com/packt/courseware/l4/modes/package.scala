package com.packt.courseware.l4

import java.time.format.DateTimeFormatter

package object modes {

  type ChatbotMode = PartialFunction[(String,EffectsProvider),Processed]

  val bye: ChatbotMode = { case ("bye", eff) => Processed("bye", bye, true) }

  val currentDate: ChatbotMode = { case ("date", effects) =>
    val currentDate = effects.currentDate()
    val message = currentDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
    Processed(message,modes.currentDate,false)
  }

  val currentTime: ChatbotMode = { case ("time", effects) =>
    val currentDate = effects.currentDate()
    val message = currentDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
    Processed(message,modes.currentTime,false)
  }

  val interestingIgnore: ChatbotMode = {
    case _ => Processed("interesting...",interestingIgnore,false)
  }

  def or(frs:ChatbotMode, snd: ChatbotMode): ChatbotMode = {
    val frsPost = frs.andThen(p => p.copy(nextMode = or(p.nextMode,snd)))
    val sndPost = snd.andThen(p => p.copy(nextMode = or(p.nextMode,frs)))
    frsPost orElse sndPost
  }

  def otherwise(frs: ChatbotMode, snd: ChatbotMode): ChatbotMode = {
    val frsPost = frs.andThen(p => p.copy(nextMode = otherwise(p.nextMode,snd)))
    val sndPost = snd.andThen(p => p.copy(nextMode = otherwise(frs,p.nextMode)))
    frsPost orElse sndPost
  }

}
