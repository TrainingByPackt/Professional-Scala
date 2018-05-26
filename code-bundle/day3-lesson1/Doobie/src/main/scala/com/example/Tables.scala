package com.example

import doobie._
import doobie.implicits._
import cats.implicits._

object Tables {

  def create: ConnectionIO[Int] =
    User.table.run *> Todo.table.run

}
