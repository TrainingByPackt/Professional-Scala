package com.packt.courseware.l3

import java.time.{LocalDate, LocalTime}

trait TimeProvider {

  def currentTime(): LocalTime

  def currentDate(): LocalDate

}


object DefaultTimeProvider extends TimeProvider {

  override def currentTime(): LocalTime = LocalTime.now()

  override def currentDate(): LocalDate = LocalDate.now()

}