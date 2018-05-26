package com.packt.courseware.l3

import java.time.{LocalDate, LocalTime}

trait EffectsProvider extends TimeProvider {

  def  input: UserInput

  def  output: UserOutput

}

object DefaultEffects extends EffectsProvider
{
  override def input: UserInput = ConsoleInput

  override def output: UserOutput = ConsoleOutput

  override def currentTime(): LocalTime = LocalTime.now()

  override def currentDate(): LocalDate = LocalDate.now()
}