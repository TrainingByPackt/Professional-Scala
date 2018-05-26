package com.example

sealed trait Error {
  def message: String
}

case object SpecialCharacters extends Error {
  val message: String = "Value can't contain special characters"
}

case object TooShort extends Error {
  val message: String = "Value is too short"
}

case object ValueTooLow extends Error {
  val message: String = "Value is too low"
}

