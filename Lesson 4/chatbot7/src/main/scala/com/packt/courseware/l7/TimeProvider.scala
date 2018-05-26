package com.packt.courseware.l7

import java.time.{LocalDate, LocalTime}

trait TimeProvider {
  def currentTime(): LocalTime

  def currentDate(): LocalDate
}
