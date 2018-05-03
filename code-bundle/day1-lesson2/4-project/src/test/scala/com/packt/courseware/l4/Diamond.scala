package com.packt.courseware.l4

trait Component
{
  def description(): String
}

trait Transmitter extends Component
{
  def generateParams(): String
}

trait Reveiver extends Component
{
  def receiverParame(): String
}

trait Radio extends Transmitter with Reveiver

