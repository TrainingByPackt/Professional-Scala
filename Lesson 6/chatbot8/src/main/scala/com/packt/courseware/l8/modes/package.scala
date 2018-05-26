package com.packt.courseware.l8

import java.time.format.DateTimeFormatter

package object modes {


  val bye: ChatbotMode = ChatbotMode.partialFunction{ case "bye" => Processed("bye", bye, true) }


  val currentTime: ChatbotMode = ChatbotMode.function{ (line,effects) =>
    line match {
      case "time" => val currentDate = effects.currentDate()
        val message = currentDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
        Some(Processed(message, modes.currentTime, false))
      case _ => None
    }
  }

  val interestingIgnore: ChatbotMode = ChatbotMode.partialFunction{ case line => Processed("interesting...",interestingIgnore,false) }



}
