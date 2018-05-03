package com.packt.courseware.l6

import java.time.{LocalDate, LocalTime}

trait TimeProvider {

  def currentTime(): LocalTime

  def currentDate(): LocalDate

}
