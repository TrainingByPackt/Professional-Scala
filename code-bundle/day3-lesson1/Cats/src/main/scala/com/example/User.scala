package com.example

import cats._
import cats.syntax._
import cats.data.{ NonEmptyList, Validated }
import cats.implicits._

final case class User(
  username: String,
  age: Int,
)

object User {

  def validate(username: String, age: Int) =
    (validateUsername(username), validateAge(age)).mapN { User.apply }

  def validateAge(age: Int): Validated[NonEmptyList[Error], Int] =
    if (age >= 1) age.validNel
    else ValueTooLow.invalidNel

  def validateUsername(username: String): Validated[NonEmptyList[Error], String] =
    (checkLength(username), checkSpecialCharacters(username)).mapN { case (a, _) => a }

  private def checkLength(str: String): Validated[NonEmptyList[Error], String] =
    if (str.length > 3) str.validNel
    else TooShort.invalidNel

  private def checkSpecialCharacters(str: String): Validated[NonEmptyList[Error], String] =
    if (str.matches("^[a-zA-Z]+$")) str.validNel
    else SpecialCharacters.invalidNel

}
