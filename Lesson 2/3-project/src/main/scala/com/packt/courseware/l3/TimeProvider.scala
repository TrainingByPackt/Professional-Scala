package com.packt.courseware.l3

import java.time.{LocalDate, LocalTime}

trait TimeProvider {

  def currentTime(): LocalTime

  def currentDate(): LocalDate

}
